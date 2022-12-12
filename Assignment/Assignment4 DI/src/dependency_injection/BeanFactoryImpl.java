package dependency_injection;


import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * TODO you should complete the class
 */
public class BeanFactoryImpl implements BeanFactory {
    Properties valueProperties = new Properties();
    Properties injectProperties = new Properties();


    @Override
    public void loadInjectProperties(File file) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            injectProperties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadValueProperties(File file) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            valueProperties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T createInstance(Class<T> clazz) {
        try {

            T classInstance;
// 解决接口注入
            if (injectProperties.getProperty(clazz.getName()) != null) {
                String className = injectProperties.getProperty(clazz.getName());
                try {
                    Class<?> cls = Class.forName(className);
                    classInstance = (T) createInstance(cls);
                    return classInstance;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            Field[] fields = clazz.getDeclaredFields(); // 获得传入类所有的成员变量
            Object[] objects = null;
            Constructor<?> constructor = null; // 获取 inject 的构造器
            System.out.println("All the constructors are " + Arrays.toString(clazz.getDeclaredConstructors()));

            for (Constructor<?> c : clazz.getDeclaredConstructors()) {

                if (c.getAnnotation(Inject.class) != null) {
                    constructor = c;
                    Parameter[] parameters = constructor.getParameters();
                    System.out.println("All the parameters are " + Arrays.toString(constructor.getParameters()));
                    objects = new Object[parameters.length];
                    int count = 0;
                    for (Parameter parameter : parameters) {
                        if (parameter.getAnnotation(Value.class) == null) { // 需要实例化的类名
                            Class<?> cls = parameter.getType();
                            objects[count] = createInstance(cls);
                        } else {
                            Value valueAnnotation = parameter.getAnnotation(Value.class);
                            System.out.println("The value of the annotation of parameter is " + valueAnnotation.toString());
                            objects[count] = extractObjectFromValue(valueAnnotation, parameter.getType(), parameter, null);
                        }
                        count++;
                    }
                    break;
                }
            }
            if (constructor == null) {
                System.out.println("No inject constructor found");
                constructor = clazz.getDeclaredConstructor();
            }

            if (objects == null) {
                classInstance = clazz.getDeclaredConstructor().newInstance(); // 获得传入类的实例
                System.out.println("Need Injected");
            } else {
                classInstance = (T) constructor.newInstance(objects); // 获得传入类的实例
                System.out.println("Directly New");
            }

            for (Field field : fields) {
                System.out.println("The field of the class is " + field.toString());
                if (field.getAnnotation(Value.class) != null) {// 遍历每一个含有 value 注释的成员变量
                    Value valueAnnotation = field.getAnnotation(Value.class); // 取出对应的 Value 类
                    System.out.println("The value of the field is " + valueAnnotation.value());
                    Object value = extractObjectFromValue(valueAnnotation, field.getType(), null, field);
                    fillField(value, field, classInstance);
                } else if (field.getAnnotation(Inject.class) != null) {
                    fillField(createInstance(field.getType()), field, classInstance);
                }
            }
            return classInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    // 从配置文件或者 value 注解当中提取 value 值，flag = false 表示是基本类型，不需要分割，flag = true 表示需要分割
    private String[] extractValueFromValueAnnotation(Value valueAnnotation, boolean flag) {
        String[] strings;
        String value = valueProperties.getProperty(valueAnnotation.value());
        if (value == null) {
            value = valueAnnotation.value();
        }
        if (!flag) { // 可以从配置文件当中找到对应的变量的情况
            // 从配置文件当中读取Value类的 value() 对应的属性值，并将其赋值给实例类的对应成员变量 field
            strings = value.split(valueAnnotation.delimiter());
        } else {
            strings = value.substring(1, value.length() - 1).split(valueAnnotation.delimiter());
        }
        return strings;
    }

    // 用 Object 填充 field
    private <T> void fillField(Object obj, Field f, T classInstance) throws IllegalAccessException {
        f.setAccessible(true);
        f.set(classInstance, obj);
        f.setAccessible(false);
    }

    private Object extractObjectFromValue(Value valueAnnotation, Class clazz, Parameter parameter, Field field) {

        Object parameterObject = null;
        if (clazz == int.class) {// 对于 int 类型的成员变量
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, false);
            if(strings.length == 1 && strings[0].equals("")){
                System.out.println("Int is empty");
                parameterObject = 0;
            }else{
                for (String str : strings) {
                    try {
                        parameterObject = Integer.parseInt(str);
                        System.out.println("The int value is " + parameterObject);
                        break;
                    } catch (Exception ignored) {
                    }
                }
            }
            if(parameterObject == null){
                parameterObject = 0;
            }

        } else if (clazz == boolean.class) {
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, false);
            if(strings.length == 1 && strings[0].equals("")){
                System.out.println("Boolean is empty");
                parameterObject = false;
            }else {
                for (String str : strings) {
                    try {
                        if (str.equalsIgnoreCase("true")) {
                            parameterObject = (Boolean.TRUE);
                            break;
                        } else if (str.equalsIgnoreCase("false")) {
                            parameterObject = (Boolean.FALSE);
                            break;
                        }
                    } catch (Exception ignored) {
                    }

                }
            }
            if(parameterObject == null){
                parameterObject = false;
            }
            System.out.println("The boolean value is " + parameterObject);

        } else if (clazz == String.class) {
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, false);
            parameterObject = strings[0];
            System.out.println("The string value is " + parameterObject);
        } else if (clazz == int[].class) {
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, true);
            if(strings.length == 1 && strings[0].equals("")){
                System.out.println("Strings is empty");
                parameterObject = new int[0];
            }else {
                ArrayList<Integer> int_arrayList = new ArrayList<>();
                for (String string : strings) {
                    try {
                        int i1 = Integer.parseInt(string);
                        int_arrayList.add(i1);
                    } catch (Exception ignored) {
                    }
                }
                System.out.print("The int[] value are [");
                int[] nums = new int[int_arrayList.size()];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = int_arrayList.get(i);
                    System.out.print(nums[i] + " ");
                }
                System.out.println(" ]");
                parameterObject = nums;
            }
        } else if (clazz == boolean[].class) {
            System.out.println("In parameter boolean[]");
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, true);
            if(strings.length == 1 && strings[0].equals("")){
                System.out.println("Strings is empty");
                parameterObject = new boolean[0];
            }else {
                ArrayList<Boolean> boolean_arrayList = new ArrayList<>();
                for (String string : strings) {
                    try {
                        if (string.equalsIgnoreCase("true")) {
                            boolean_arrayList.add(true);
                        } else if (string.equalsIgnoreCase("false")) {
                            boolean_arrayList.add(false);
                        }
                    } catch (Exception ignored) {
                    }
                }

                System.out.print("The boolean[] value are [");
                boolean[] nums = new boolean[boolean_arrayList.size()];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = boolean_arrayList.get(i);
                    System.out.print(nums[i] + " ");
                }
                System.out.println(" ]");
                parameterObject = nums;
            }
        } else if (clazz == String[].class) {
            String[] strings = extractValueFromValueAnnotation(valueAnnotation, true);
            if(strings.length == 1 && strings[0].equals("")){
                System.out.println("Strings is empty");
                parameterObject = new String[0];
            }else {
                System.out.print("The String[] value is [ ");
                for (String str : strings) {
                    System.out.print(str + " ");
                }
                System.out.println(" ]");
                parameterObject = strings;
            }
        } else if (clazz == List.class) {
            Class<?> elementType;
            try {
                if (parameter != null) {
                    elementType = Class.forName(((ParameterizedType) parameter.getParameterizedType())
                            .getActualTypeArguments()[0].getTypeName());
                } else {
                    elementType = Class.forName(((ParameterizedType) field.getGenericType())
                            .getActualTypeArguments()[0].getTypeName());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String[] element = extractValueFromValueAnnotation(valueAnnotation, true);
            List<Object> elementList = new ArrayList<>();
            if(element.length == 1 && element[0].equals("")){
                System.out.println("List is empty");
            }else {
                if (elementType == String.class) {
                    for (String str : element) {
                        if (str != null) {
                            elementList.add(str);
                        }
                    }
                } else if (elementType == Integer.class) {
                    for (String str : element) {
                        try {
                            int listIntValue = Integer.parseInt(str);
                            elementList.add(listIntValue);
                        } catch (Exception ignored) {
                        }
                    }
                } else if (elementType == Boolean.class) {
                    for (String str : element) {
                        try {
                            if (str.equalsIgnoreCase("true")) {
                                elementList.add(Boolean.TRUE);
                            } else if (str.equalsIgnoreCase("false")) {
                                elementList.add(Boolean.FALSE);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
                System.out.println(elementList);
            }
            parameterObject = elementList;
        } else if (clazz == Map.class) {
            //find parameter type of Map

            Class<?> keyType;
            Class<?> valueType;
            try {
                if (parameter != null) {
                    keyType = Class.forName(((ParameterizedType) parameter.getParameterizedType())
                            .getActualTypeArguments()[0].getTypeName());
                    valueType = Class.forName(((ParameterizedType) parameter.getParameterizedType())
                            .getActualTypeArguments()[1].getTypeName());
                } else {
                    keyType = Class.forName(((ParameterizedType) field.getGenericType())
                            .getActualTypeArguments()[0].getTypeName());
                    valueType = Class.forName(((ParameterizedType) field.getGenericType())
                            .getActualTypeArguments()[1].getTypeName());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String[] elements = extractValueFromValueAnnotation(valueAnnotation, true);
            Map<Object, Object> mapObj = new HashMap<>();
            if(elements.length == 1 && elements[0].equals("")){
                System.out.println("Map is empty");
            }else {
                for (String element : elements) {
                    if (element.equals("")) {
                        continue;
                    }
                    System.out.println("Element in Map: " + element);
                    String key = element.split(":")[0];
                    String value = element.split(":")[1];
                    if (keyType == String.class && valueType == Integer.class) {
                        try {
                            mapObj.put(key, Integer.parseInt(value));
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == String.class && valueType == String.class) {
                        mapObj.put(key, value);
                    } else if (keyType == String.class && valueType == Boolean.class) {
                        try {
                            if (value.equalsIgnoreCase("true")) {
                                mapObj.put(key, true);
                            } else if (value.equalsIgnoreCase("false")) {
                                mapObj.put(key, false);
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Integer.class && valueType == String.class) {
                        try {
                            mapObj.put(Integer.parseInt(key), value);
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Integer.class && valueType == Integer.class) {
                        try {
                            mapObj.put(Integer.parseInt(key), Integer.parseInt(value));
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Integer.class && valueType == Boolean.class) {
                        try {
                            if (value.equalsIgnoreCase("true")) {
                                mapObj.put(Integer.parseInt(key), true);
                            } else if (value.equalsIgnoreCase("false")) {
                                mapObj.put(Integer.parseInt(key), false);
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Boolean.class && valueType == String.class) {
                        try {
                            if (key.equalsIgnoreCase("true")) {
                                mapObj.put(true, value);
                            } else if (key.equalsIgnoreCase("false")) {
                                mapObj.put(false, value);
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Boolean.class && valueType == Integer.class) {
                        try {
                            if (key.equalsIgnoreCase("true")) {
                                mapObj.put(true, Integer.parseInt(value));

                            } else if (key.equalsIgnoreCase("false")) {
                                mapObj.put(false, Integer.parseInt(value));

                            }
                        } catch (Exception ignored) {
                        }
                    } else if (keyType == Boolean.class && valueType == Boolean.class) {
                        try {
                            if (key.equalsIgnoreCase("true") && value.equalsIgnoreCase("true")) {
                                mapObj.put(true, true);
                            } else if (key.equalsIgnoreCase("true") && value.equalsIgnoreCase("false")) {
                                mapObj.put(true, false);
                            } else if (key.equalsIgnoreCase("false") && value.equalsIgnoreCase("true")) {
                                mapObj.put(false, true);
                            } else if (key.equalsIgnoreCase("false") && value.equalsIgnoreCase("false")) {
                                mapObj.put(false, false);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }

                System.out.print("The value of map are {");
                for (Map.Entry<Object, Object> entry : mapObj.entrySet()) {
                    System.out.print(" Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
                System.out.println("}");
            }
            parameterObject = mapObj;
        } else if (clazz == Set.class) {
            Class<?> elementType;
            try {
                if (parameter != null) {
                    elementType = Class.forName(((ParameterizedType) parameter.getParameterizedType())
                            .getActualTypeArguments()[0].getTypeName());
                } else {
                    elementType = Class.forName(((ParameterizedType) field.getGenericType())
                            .getActualTypeArguments()[0].getTypeName());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String[] element = extractValueFromValueAnnotation(valueAnnotation, true);
            Set<Object> set = new HashSet<>();
            if(element.length == 1 && element[0].equals("")){
                System.out.println("Set is empty");
            }else {

                for (String str : element) {
                    if (elementType == Integer.class) {
                        try {
                            set.add(Integer.parseInt(str));
                        } catch (Exception ignored) {
                        }
                    } else if (elementType == Boolean.class) {
                        try {
                            if (str.equalsIgnoreCase("true")) {
                                set.add(true);
                            } else if (str.equalsIgnoreCase("false")) {
                                set.add(false);
                            }
                        } catch (Exception ignored) {
                        }

                    }
                }
                //方法3 增强型for循环遍历
                for (Object value : set) {
                    System.out.println(value.toString());
                }
            }
            parameterObject = set;
        }
        return parameterObject;
    }

}
