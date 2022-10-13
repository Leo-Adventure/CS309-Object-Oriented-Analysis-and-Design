<template>
    <div class="main">

        <el-container>
            <el-header class="header"><b>Open Courses</b></el-header>
            <div class="table_a">
                <el-table :data="tableData"
                    :header-cell-style="{color:'black',fontSize: '14px',fontFamily: 'nano',background:'lightgrey'}"
                    border stripe fit style="width: 100%" empty-text="No Course opening">
                    <el-table-column prop="course_name" label="Course Name" width="180" align="center">
                    </el-table-column>
                    <el-table-column prop="course_code" label="Course Code" width="180" align="center">
                    </el-table-column>
                    <el-table-column prop="language" label="Language" width="90" align="center">
                    </el-table-column>
                    <el-table-column prop="teacher" label="Teacher" width="80" align="center">
                    </el-table-column>
                    <el-table-column prop="date" label="Date" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="time" label="Time" width="60" align="center">
                    </el-table-column>
                    <el-table-column prop="location" label="Location" align="center">
                    </el-table-column>
                    <el-table-column prop="duration" label="Duration" width="120" align="center">
                    </el-table-column>
                    <el-table-column prop="operation" label="Operation" align="center">
                        <div>
                            <el-button type="primary" size="mini" plain round @click="editClass">edit</el-button>
                        </div>

                        <div>
                            <el-button type="danger" size="mini" plain round @click="deleteClass">delete</el-button>
                        </div>

                    </el-table-column>
                </el-table>


                <div class="button_div">
                    <el-button type="primary" @click="addClass">Add Class</el-button>
                </div>

                <el-dialog :visible.sync="dialogFormVisible">
                    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                        <el-form-item>
                            <div class="form_header">
                                <h1><b>Add Course</b></h1>
                            </div>
                        </el-form-item>
                        <el-form-item label="Course Name" prop="course_name">
                            <el-input v-model="form.course_name"></el-input>
                        </el-form-item>
                        <el-form-item label="Couse Code" prop="course_code">
                            <el-input v-model="form.course_code"></el-input>
                        </el-form-item>
                        <el-form-item label="Language" prop="language">
                            <el-radio-group v-model="form.language">
                                <el-radio label="Chinese"></el-radio>
                                <el-radio label="English"></el-radio>
                                <el-radio label="Bilingual"></el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="Teacher" prop="teacher">
                            <el-input v-model="form.teacher"></el-input>
                        </el-form-item>
                        <el-form-item label="Date" prop="date">
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="2022/10/12" format="yyyy/MM/dd"
                                    :picker-options="pickerOptions" v-model="form.date" style="width: 100%;">
                                </el-date-picker>
                            </el-col>

                        </el-form-item>
                        <el-form-item label="Time" prop="time">
                            <el-col :span="11">
                                <el-time-picker placeholder="--:--" v-model="form.time"  style="width: 100%;">
                                </el-time-picker>
                            </el-col>
                        </el-form-item>

                        <el-form-item label="Location" prop="location">
                            <el-col :span="20">
                                <el-select v-model=form.location filterable
                                    placeholder="Teaching Building NO.1 Lecture Hall">
                                    <el-option v-for="item in options" :key="item.value" :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-col>
                        </el-form-item>

                        <el-form-item label="Duration" prop="duration">
                            <el-input v-model="form.duration"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <div class="button_div">
                                <el-button type="primary" size="small" @click="submitForm('form')">Submit</el-button>
                                <el-button size="small" class="normal_button" @click="resetForm('form')">Cancel
                                </el-button>
                            </div>
                        </el-form-item>

                    </el-form>
                </el-dialog>

            </div>
        </el-container>
    </div>
</template>
<meta charset=​"UTF-8">​
<script>

export default {
    data() {
        var validateDuration = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter duration'));
            } else {
                let isNumber = /^[0-9]+$/.test(value)
                if (!isNumber) {
                    callback(new Error('Duration must be a number'));
                }
                else {
                    callback();
                }
            }
        };

        var validateCourseName = (rule, value, callback) => {
            if (value == '') {
                callback(new Error('Please enter course name'));
            } else {
                let isEN = /^[a-zA-Z]+$/.test(value);
                if (!isEN) {
                    callback(new Error('Course Name can only be English letters'));
                } else {
                    callback();
                }
            }
        };
        var validateCourseCode = (rule, value, callback) => {
            if (value == '') {
                callback(new Error('Please enter course name'));
            } else {
                let isEN = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$/.test(value);
                if (!isEN) {
                    callback(new Error('Course Code should be a combination of letters and numbers'));
                } else {
                    callback();
                }
            }
        };


        return {
            dialogFormVisible: false,
            tableData: [{
                course_name: 'OOAD',
                course_code: 'CS309',
                language: 'English',
                teacher: 'XXX',
                date: '2022/09/30',
                time: '19:00',
                location: 'Activity Room',
                duration: '3.0h',

            }],
            form: {
                course_name: '',
                course_code: '',
                language: '',
                teacher: '',
                date: '',
                time: '',
                location: '',
                duration: '',
            },
            pickerOptions: {
                disabledDate(v) {
                    return v.getTime() < new Date().getTime() - 86400000;
                }
            },
            options: [{
                value: 'option1',
                label: 'Teaching Building NO.1 Lecture Hall'
            }, {
                value: 'option2',
                label: 'Research Building Lecture Hall'
            }, {
                value: 'option3',
                label: 'Library Conference Hall'
            },
            {
                value: 'option4',
                label: 'Activity Room'
            }],
            rules: {
                course_name: [
                    { validator: validateCourseName, trigger: 'blur' },
                ],
                course_code: [
                    { validator: validateCourseCode, trigger: 'blur' },
                ],
                language: [
                    { required: true, message: 'Please enter the language of the course', trigger: 'change' }
                ],
                teacher: [
                    { required: true, message: 'Please enter the teacher of the course', trigger: 'blur' }
                ],

                date: [

                    { type: 'date', required: true, message: 'Please select the time of the course', trigger: 'change' }
                ],
                time: [
                    { type: 'date', required: true, message: 'Please select the time of the course', trigger: 'change' }
                ],
                location: [
                    { required: true, message: 'Please enter location of the course', trigger: 'change' }
                ],
                duration: [
                    { validator: validateDuration, trigger: 'blur' }
                ],
            }
        };
    },

    methods: {
        addClass() {
            this.dialogFormVisible = true;
        },
        editClass() {
            this.dialogFormVisible = true;
        },
        deleteClass() {
            this.$alert("Sure to delete?")
        },
        submitForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }

    }

}
</script>


<style lang="scss">
.header {
    font-family: 'nano';
    background-color: #f4ce69;
    line-height: 60px;
    text-align: center;
    font-size: 40px;
}

.button_div {
    text-align: center;
}

.el-button--danger {
    margin-top: 10px;
    height: 30px;
    line-height: 0.4;
    background-color: rgb(245, 172, 164);

    border: 1px solid rgba(239, 156, 156, 0.027);
    border-radius: 1px;
    text-align: center;

    span {
        font-family: 'nano';
        color: rgb(4, 4, 4);
        font-size: 14px;
        letter-spacing: 1px;
    }
}

.el-button--primary {

    margin-top: 15px;
    height: 30px;
    line-height: 0.4;
    background-color: #f4ce69;
    border: 1px solid rgb(236, 133, 14);
    border-radius: 1px;
    text-align: center;

    span {
        font-family: 'nano';
        color: rgb(4, 4, 4);
        font-size: 14px;
        letter-spacing: 1px;
    }
}



.el-table {
    //background: linear-gradient(to bottom left, #3b818c 13%,#29b7cb 10%, #3b818c 80%, ),
    //  linear-gradient(to left, rgba(255, 255, 255, 0.12) 0%, rgba(190, 65, 65, 0.12) 100%);
    // background-blend-mode: screen;
    // background-color: transparent; //这是设置透明背景色
    //text-align: center;
    padding: 0%;
}

.form_header {
    font-family: 'nano';

    text-align: center;
}

.el-select>.el-input {
    display: block;
    padding-right: 80px;
}

.normal_button {
    height: 30px;
    line-height: 0.4;
    border-radius: 1px;
    text-align: center;

    span {
        font-family: 'nano';
        color: rgb(4, 4, 4);
        font-size: 14px;
        letter-spacing: 1px;
    }
}
</style>