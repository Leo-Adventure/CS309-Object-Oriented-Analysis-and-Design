<script lang="ts" setup>
// import { time } from 'console';
import { Console } from 'console';
import type { FormInstance, FormRules } from 'element-plus'
import { ref, reactive } from 'vue';

defineProps({
  msg: String
})
const formSize = ref('default')
const today = new Date();
const ruleFormRef = ref<FormInstance>()
// 设计这么一个中间值方便填进去
const ruleForm = reactive({
  CourseName: 'XXXX',
  CourseCode: 'XXX',
  Language: 'English',
  Teacher: 'XXX',
  Date: '',
  Time: '',
  // Date: today.toLocaleDateString,
  // Time: today.toLocaleTimeString,
  Location: 'Teaching Building No.1 Lecture Hall',
  Duration: '2',

});

const ruleFormRef2 = ref<FormInstance>()
// 设计这么一个中间值方便填进去
const ruleForm2 = reactive({
  CourseName: 'XXXX',
  CourseCode: 'XXX',
  Language: 'English',
  Teacher: 'XXX',
  Date: '',
  Time: '',
  Location: 'Teaching Building No.1 Lecture Hall',
  Duration: '2',
  CurEditIndex: -1,

});
function Course(CourseName, CourseCode, Language, Teacher, date, time_in, location: string, duration) {
  this.CourseName = CourseName;
  this.CourseCode = CourseCode;
  this.Language = Language;
  this.Teacher = Teacher;
  this.Date = date;
  this.Time = time_in;
  this.Location = location;
  this.Duration = duration;
};

const checkCourseCode = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('Please input non-blank value'))
  }
  setTimeout(() => {
    var step = 0;
    var flag = false;
    for (; step < TableCourses.length; step++) {
      if (TableCourses[step].CourseName == ruleForm.CourseName && value != TableCourses[step].CourseCode) {
        // console.log(value) 
        // console.log(TableCourses[step].CourseCode) // 强烈建议js变量名小写开头
        flag = true;
        break;
      }
    }

    if (flag == true) {
      callback(new Error("同一个名字课程课程代号请保持一致"))
    } else {
      callback()
    }
  }, 500)
};

const checkDate = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('Please input non-blank value'))
  }
  setTimeout(() => {
    const adate = new Date();
    const return_date = new Date(value);
    var flag = true;
    if (return_date <= adate) {
      callback(new Error('请输入一个以后的时间'))
    } else {
      var current_CourseCode;
      var current_Date;
      var current_Teacher;
      if (dialogFormVisible.value == true) {
        current_CourseCode = ruleForm.CourseCode
        current_Date = ruleForm.Date
        current_Teacher = ruleForm.Teacher
      } else if (dialogFormVisible2.value == true) {
        current_CourseCode = ruleForm2.CourseCode
        current_Date = ruleForm2.Date
        current_Teacher = ruleForm2.Teacher
      } else {
        console.log("error at LINE 97")
      }
      console.log(current_Teacher)
      console.log(dialogFormVisible.value)
      console.log(dialogFormVisible2.value)
      for (var step = 0; step < TableCourses.length; step++) {
        if (dialogFormVisible2.value == true && ruleForm2.CurEditIndex == step) {
          continue
        }
        if (TableCourses[step].CourseCode == current_CourseCode && TableCourses[step].Date == current_Date) {
          callback(new Error('一天一节课只能上一次'))
          flag = false;
          break;
        }
        if (TableCourses[step].Teacher == current_Teacher && TableCourses[step].Date == current_Date) {
          callback(new Error('一个老师一天只能上课一次'))
          flag = false;
          break;
        }
      }
      if (flag == true) {
        callback()
      }

    }
  }, 500)
};
const checkCourseLocation = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('Please input non-blank value'))
  }
  setTimeout(() => {
    var step = 0;
    var flag = false;
    for (; step < TableCourses.length; step++) {
      if (dialogFormVisible2.value == true && ruleForm2.CurEditIndex == step) {
        continue
      }
      if (TableCourses[step].Location == value) {
        const start_time_1 = new Date(TableCourses[step].Date + " " + TableCourses[step].Time)
        const end_time_1 = new Date()
        end_time_1.setTime(start_time_1.getTime() + 3600 * 1000 * parseFloat(TableCourses[step].Duration.substring(0, TableCourses[step].Duration.length - 1)))
        var start_time_2;
        var end_time_2 = new Date();
        if (dialogFormVisible.value == true) {
          start_time_2 = new Date(ruleForm.Date + " " + ruleForm.Time)
          end_time_2.setTime(start_time_2.getTime() + 3600 * 1000 * parseFloat(ruleForm.Duration))
        } else if (dialogFormVisible2.value == true) {
          start_time_2 = new Date(ruleForm2.Date + " " + ruleForm2.Time)
          end_time_2.setTime(start_time_2.getTime() + 3600 * 1000 * parseFloat(ruleForm2.Duration))
        } else {
          console.log("error at LINE 128")
        }


        console.log(start_time_1);
        console.log(start_time_2);
        console.log(end_time_1);
        console.log(end_time_2);
        if (start_time_1 <= start_time_2 && start_time_2 <= end_time_1) {
          flag = true;
          break;
        } else if (start_time_1 >= start_time_2 && start_time_1 <= end_time_2) {
          flag = true;
          break;
        }
      }
    }
    if (flag == true) {
      callback(new Error("同一间教室使用时间不能重叠"))
    } else {
      callback()
    }
  }, 500)
};

const rules = reactive({
  CourseName: [
    { required: true, message: '请输入课程名称', trigger: 'blur' },
    { pattern: "^[A-Za-z]+$", message: "课程名称只能包含英文", trigger: 'blur' },
  ],
  CourseCode: [
    { required: true, message: '请输入课程编号', trigger: 'blur' },
    { pattern: "^[A-Za-z0-9]+$", message: "课程编号只能包含英文和数字", trigger: 'blur' },
    { validator: checkCourseCode, trigger: 'blur' },
  ],
  Lauguage: [
    { required: true, message: '请输入语言', trigger: 'blur' },
  ],
  Duration: [
  { required: true, message: '请输入数字', trigger: 'blur' },
    { pattern: "^[0-9.]+$", message: "时间只能包含数字", trigger: 'blur' },
  ],
  Date: [
    { validator: checkDate, trigger: 'blur' }
  ],
  Location: [
    { validator: checkCourseLocation, trigger: 'blur' }
  ],
  Teacher: [
    { required: true, message: '请输入老师名字', trigger: 'blur' },
  ]

})


const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  // ruleForm.Date = new Date(ruleForm.Date).toLocaleDateString()
  // ruleForm.Date = new Date(ruleForm.Date).toLocaleDateString()
  // 
  await formEl.validate((valid, fields) => {
    if (valid) {
      ruleForm.Duration = ruleForm.Duration + "H"
      TableCourses.push(JSON.parse(JSON.stringify(ruleForm)))
      console.log('submit!')
      dialogFormVisible.value = false;
    } else {
      console.log('error submit!', fields)
    }
  })
}

const editForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      ruleForm2.Duration = ruleForm2.Duration + "H"
      TableCourses[ruleForm2.CurEditIndex] = JSON.parse(JSON.stringify(ruleForm2))
      console.log('submit!')
      dialogFormVisible2.value = false;
    } else {
      console.log('error submit!', fields)
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

const TableCourses = reactive([
  new Course('OOADA', 'cs3091', 'English', 'ZYQ', '2022/10/30', '19:00', "Teaching Building No.1 Lecture Hall", "3H"),
  new Course('OOADB', 'cs3092', 'English', 'ZYM', '2022/10/30', '19:00', "Research Building Lecture Hall", "3H"),
  new Course('OOADC', 'cs3093', 'English', 'LZZ', '2022/10/30', '19:00', "Library Conference Hall and Activity Room", "3H")
]);

const dialogFormVisible = ref(false)

const dialogFormVisible2 = ref(false)

const handleEdit = (index: number, row) => {
  ruleForm2.CourseName = TableCourses[index].CourseName
  ruleForm2.CourseCode = TableCourses[index].CourseCode
  ruleForm2.Date = TableCourses[index].Date
  ruleForm2.Duration = TableCourses[index].Duration
  ruleForm2.Language = TableCourses[index].Language
  ruleForm2.Location = TableCourses[index].Location
  ruleForm2.Teacher = TableCourses[index].Teacher
  ruleForm2.Time = TableCourses[index].Time
  ruleForm2.CurEditIndex = index
  dialogFormVisible2.value = true
}
// 不能用index的方式
const handleDelete = (index: number, row) => {
  TableCourses.splice(index, 1)
}





</script>

<template>


  <h1 align="center">
    OpenCourse
  </h1>



  <el-table :data="TableCourses" stripe style="width: 100%" border>
    <el-table-column prop="CourseName" label="courseName" />
    <el-table-column prop="CourseCode" label="courseCode" />
    <el-table-column prop="Language" label="language" />

    <el-table-column prop="Teacher" label="teacher" />
    <el-table-column prop="Date" label="date" />
    <el-table-column prop="Time" label="time" />
    <el-table-column prop="Location" label="location" />
    <el-table-column prop="Duration" label="duration(H)" />
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.$index, scope.row)">
          <template #reference>
            <el-button type="danger">Delete</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogFormVisible" title="添加课程">
    <!-- 这个ref绝了,v-model显然是存储了数据对象 -->
    <!-- <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px" class="demo-ruleForm" status-icon> -->
    <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Course Name" prop="CourseName">
        <el-input v-model="ruleForm.CourseName" />
      </el-form-item>
      <el-form-item label="Course Code" prop="CourseCode">
        <el-input v-model="ruleForm.CourseCode" />
      </el-form-item>

      <el-form-item label="Language" prop="Language">
        <el-radio-group v-model="ruleForm.Language">
          <el-radio label="Chinese" />
          <el-radio label="English" />
          <el-radio label="Bilingual" />
        </el-radio-group>
      </el-form-item>

      <el-form-item label="Teacher" prop="Teacher">
        <el-input v-model="ruleForm.Teacher" />
      </el-form-item>


      <el-form-item label="Date" required>

        <el-form-item prop="Date">
          <el-date-picker v-model="ruleForm.Date" type="date" label="Pick a date" placeholder="Pick a date"
            style="width: 100%" value-format="YYYY/MM/DD" />
        </el-form-item>

      </el-form-item>
      <el-form-item label="Time" required>

        <el-form-item prop="Time">
          <el-time-picker v-model="ruleForm.Time" type="datetime" label="Pick time" placeholder="Pick time"
            style="width: 100%" format="HH:mm" value-format="HH:mm" />
        </el-form-item>

      </el-form-item>
      <el-form-item label="Location" prop="Location">
        <el-select v-model="ruleForm.Location" placeholder="location">
          <el-option label="Teaching Building No.1 Lecture Hall" value="Teaching Building No.1 Lecture Hall" />
          <el-option label="Research Building Lecture Hall" value="Research Building Lecture Hall" />
          <el-option label="Library Conference Hall and Activity Room"
            value="Library Conference Hall and Activity Room" />
        </el-select>
      </el-form-item>
      <el-form-item label="Duration" prop="Duration">
        <el-input v-model="ruleForm.Duration" />
      </el-form-item>
      <el-form-item warp>
        <el-button type="primary" @click="submitForm(ruleFormRef)">Create</el-button>
        <br>
        <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-dialog v-model="dialogFormVisible2" title="修改课程">
    <el-form ref="ruleFormRef2" :model="ruleForm2" status-icon :rules="rules" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Course Name" prop="CourseName">
        <el-input v-model="ruleForm2.CourseName" />
      </el-form-item>
      <el-form-item label="Course Code" prop="CourseCode">
        <el-input v-model="ruleForm2.CourseCode" />
      </el-form-item>

      <el-form-item label="Language" prop="Language">
        <el-radio-group v-model="ruleForm2.Language">
          <el-radio label="Chinese" />
          <el-radio label="English" />
          <el-radio label="Bilingual" />
        </el-radio-group>
      </el-form-item>

      <el-form-item label="Teacher" prop="Teacher">
        <el-input v-model="ruleForm2.Teacher" />
      </el-form-item>


      <el-form-item label="Date" required>

        <el-form-item prop="Date">
          <el-date-picker v-model="ruleForm2.Date" type="date" label="Pick a date" placeholder="Pick a date"
            style="width: 100%" value-format="YYYY/MM/DD" />
        </el-form-item>

      </el-form-item>
      <el-form-item label="Time" required>

        <el-form-item prop="Time">
          <el-time-picker v-model="ruleForm2.Time" type="datetime" label="Pick time" placeholder="Pick time"
            style="width: 100%" format="HH:mm" value-format="HH:mm" />
        </el-form-item>

      </el-form-item>
      <el-form-item label="Location" prop="Location">
        <el-select v-model="ruleForm2.Location" placeholder="location">
          <el-option label="Teaching Building No.1 Lecture Hall" value="Teaching Building No.1 Lecture Hall" />
          <el-option label="Research Building Lecture Hall" value="Research Building Lecture Hall" />
          <el-option label="Library Conference Hall and Activity Room"
            value="Library Conference Hall and Activity Room" />
        </el-select>
      </el-form-item>
      <el-form-item label="Duration" prop="Duration">
        <el-input v-model="ruleForm2.Duration" />
      </el-form-item>
      <el-form-item warp>
        <el-popconfirm title="Are you sure to change this?" @confirm="editForm(ruleFormRef2)">
          <template #reference>
            <el-button type="primary">Edit</el-button>
          </template>
        </el-popconfirm>
        <br>
        <el-button @click="resetForm(ruleFormRef2)">Reset</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-button text @click="dialogFormVisible = true" style="font-size: 20px; display:block;margin:0 auto" size="large"
    type="primary">open a Form nested Dialog</el-button>
</template>


<style scoped>
.read-the-docs {
  color: #888;
}

.el-loading-spinner {
  font-size: 80px;
  font-weight: bold;
}
</style>
