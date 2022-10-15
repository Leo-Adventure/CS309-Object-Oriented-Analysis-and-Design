<template>
    <div class="main">

        <canvas id="particle-canvas" width="2232" height="608"></canvas>


        <div class="content">
            <div class="header"><b>Open Courses®</b></div>

            <div class="table_a">
                <el-table :data="tableData"
                    :header-cell-style="{color:'black',fontSize: '14px',fontFamily: 'nano',background:'rgb(86, 212, 223)'}" border
                    stripe fit highlight-current-row style="width: 100%" empty-text="No Course opening" :key="num"
                    :row-style="setRowStyle">


                    <el-table-column prop="course_name" label="Course Name" width="180" align="center"  >
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
                        <template #default="scope">
                            <div>
                                <el-button type="primary" size="mini" plain round @click="handleEdit(scope.$index)">
                                    edit
                                </el-button>
                            </div>

                            <div>
                                <el-button type="danger" size="mini" plain round @click="deleteClass(scope.$index)">
                                    delete</el-button>
                            </div>
                        </template>

                    </el-table-column>
                </el-table>



                <div class="button_div">
                    <el-button type="primary" @click="addClass" round size="medium">Add Class</el-button>
                </div>
            </div>
            <el-dialog :visible.async="dialogFormVisible" :beforeClose="handleClose">
                <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                    <el-form-item>
                        <div class="form_header">
                            <h1><b>Add Course</b></h1>
                        </div>
                    </el-form-item>
                    <el-form-item label="Course Name" prop="course_name">
                        <el-input v-model="form.course_name"></el-input>
                    </el-form-item>
                    <el-form-item label="Course Code" prop="course_code">
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
                    <el-form-item label="Date" prop="date" required>
                        <el-row>
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="2022/10/14" v-model="form.date"
                                    style="width: 100%;">
                                </el-date-picker>
                            </el-col>
                        </el-row>

                    </el-form-item>
                    <el-form-item label="Time" prop="time">
                        <el-col :span="11">
                            <el-time-picker :editable="false" format="HH:mm" value-format="HH:mm" placeholder="--:--"
                                v-model="form.time" style="width: 100%;">
                            </el-time-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="Location" prop="location">
                        <el-select v-model="form.location" placeholder="">
                            <el-option label="Teaching Building NO.1 Lecture Hall"
                                value="Teaching Building NO.1 Lecture Hall"></el-option>
                            <el-option label="Library Conference Hall" value="Library Conference Hall"></el-option>
                            <el-option label="Research Building Lecture Hall" value="Research Building Lecture Hall">
                            </el-option>
                            <el-option label="Activity Room" value="Activity Room"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="Duration(h)" prop="duration">
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


            <el-dialog :visible.async="dialogFormVisible2" :beforeClose="handleClose">
                <el-form ref="form2" :model="form2" :rules="rules" label-width="100px">
                    <el-form-item>
                        <div class="form_header">
                            <h1><b>Edit Course</b></h1>
                        </div>
                    </el-form-item>
                    <el-form-item label="Course Name" prop="course_name">
                        <el-input v-model="form2.course_name"></el-input>
                    </el-form-item>
                    <el-form-item label="Course Code" prop="course_code">
                        <el-input v-model="form2.course_code"></el-input>
                    </el-form-item>
                    <el-form-item label="Language" prop="language">
                        <el-radio-group v-model="form2.language">
                            <el-radio label="Chinese"></el-radio>
                            <el-radio label="English"></el-radio>
                            <el-radio label="Bilingual"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="Teacher" prop="teacher">
                        <el-input v-model="form2.teacher"></el-input>
                    </el-form-item>
                    <el-form-item label="Date" prop="date">
                        <el-col :span="11">
                            <el-date-picker type="date" placeholder="2022/10/14" v-model="form2.date"
                                style="width: 100%;">
                            </el-date-picker>
                        </el-col>

                    </el-form-item>
                    <el-form-item label="Time" prop="time">
                        <el-col :span="11">
                            <el-time-picker placeholder="--:--" format="HH:mm" value-format="HH:mm" v-model="form2.time"
                                style="width: 100%;">
                            </el-time-picker>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="Location" prop="location">
                        <el-select v-model="form2.location" placeholder="Teaching Building NO.1 Lecture Hall">
                            <el-option label="Teaching Building NO.1 Lecture Hall"
                                value="Teaching Building NO.1 Lecture Hall"></el-option>
                            <el-option label="Library Conference Hall" value="Library Conference Hall"></el-option>
                            <el-option label="Research Building Lecture Hall" value="Research Building Lecture Hall">
                            </el-option>
                            <el-option label="Activity Room" value="Activity Room"></el-option>
                        </el-select>
                    </el-form-item>


                    <el-form-item label="Duration(H)" prop="duration">
                        <el-input v-model="form2.duration"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div class="button_div">
                            <el-button type="primary" size="small" @click="editForm('form2')">Edit</el-button>
                            <el-button size="small" class="normal_button" @click="resetForm2('form2')">Cancel
                            </el-button>
                        </div>
                    </el-form-item>

                </el-form>
            </el-dialog>

        </div>

    </div>
</template>
<meta charset=​"UTF-8">​
<script>
import moment from 'moment'

import { start } from '@/assets/js/login';
export default {
    mounted() {
        start()
    },
    data() {

        var validateDuration = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter duration'));
            } else {
                let isNumber = /^[0-9.]+$/.test(value)
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
                    var step = 0;
                    var flag = false;
                    for (; step < this.tableData.length; step++) {
                        if (this.tableData[step].course_code == this.form.course_code && value != this.tableData[step].course_name) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag == true) {
                        callback(new Error("Different courses should have different course codes"))
                    } else {
                        callback()
                    }

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
                    var step = 0;
                    var flag = false;
                    for (; step < this.tableData.length; step++) {
                        if (this.tableData[step].course_name == this.form.course_name && value != this.tableData[step].course_code) {

                            flag = true;
                            break;
                        }
                    }

                    if (flag == true) {
                        callback(new Error("Different courses should have different course codes"))
                    } else {
                        callback()
                    }
                }
            }
        };
        var validateLocation = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('Please input non-blank value'))
            }

            var step = 0;
            var flag = false;
            for (; step < this.tableData.length; step++) {

                if (this.tableData[step].date != this.format_date(this.form.date)) {
                    continue;
                }
                console.log(this.tableData[step].date)
                console.log(this.format_date(this.form.date))

                if (this.tableData[step].location == value) {
                    console.log("This date = ", this.format_date(this.tableData[step].date))
                    console.log("this.tableData[step].time = ", this.tableData[step].time)
                    const start_time_1 = new Date(this.tableData[step].date + " " + this.tableData[step].time)
                    const end_time_1 = new Date()
                    end_time_1.setTime(start_time_1.getTime() + 3600 * 1000 * parseFloat(this.tableData[step].duration.substring(0, this.tableData[step].duration.length - 1)))
                    var start_time_2;
                    var end_time_2 = new Date();
                    if (this.dialogFormVisible == true) {
                        start_time_2 = new Date(this.format_date(this.form.date) + " " + this.form.time)
                        end_time_2.setTime(start_time_2.getTime() + 3600 * 1000 * parseFloat(this.form.duration))
                    } else if (this.dialogFormVisible2 == true) {
                        start_time_2 = new Date(this.format_date(this.form2.date) + " " + this.form2.time)
                        end_time_2.setTime(start_time_2.getTime() + 3600 * 1000 * parseFloat(this.form2.duration))
                    }
                    console.log(start_time_1)
                    console.log(end_time_1)
                    console.log("----------")
                    console.log(start_time_2)
                    console.log(end_time_2)

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
                callback(new Error("Any two different courses cannot share one room at the same time"))
            } else {
                callback()
            }
        }
        var validateDate = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('Please input non-blank date'))
            }

            const adate = new Date();
            const return_date = new Date(value);
            var flag = true;
            if (return_date <= adate) {
                callback(new Error('Please enter a date after the current date'))
            }
            var current_CourseCode;
            var current_Date;
            var current_Teacher;
            if (this.dialogFormVisible == true) {
                current_CourseCode = this.form.course_code
                current_Date = this.format_date(this.form.date)
                current_Teacher = this.form.teacher
            } else if (this.dialogFormVisible2 == true) {
                current_CourseCode = this.form2.course_code
                current_Date = this.format_date(this.form2.date)
                current_Teacher = this.form2.teacher
            }

            for (var step = 0; step < this.tableData.length; step++) {

                if (this.dialogFormVisible2 == true && this.form2.index == step) {
                    continue
                }
                if (this.tableData[step].course_code == current_CourseCode && this.tableData[step].date == current_Date) {
                    callback(new Error('One course is scheduled at most once a day'))
                    flag = false;
                    break;
                }
                if (this.tableData[step].teacher == current_Teacher && this.tableData[step].date == current_Date) {
                    callback(new Error('Each teacher can take no more than one lecture per day'))
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                callback()
            }
        }




        return {
            num: '',
            dialogFormVisible: false,
            dialogFormVisible2: false,
            tableData: [],
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
            form2: {
                course_name: '',
                course_code: '',
                language: '',
                teacher: '',
                date: '',
                time: '',
                location: '',
                duration: '',
                index: -1,
            },

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

                    { validator: validateDate, trigger: 'change' }
                ],
                time: [
                    { required: true, message: 'Please select the time of the course', trigger: 'change' }
                ],
                location: [
                    { validator: validateLocation, trigger: 'change' }
                ],
                duration: [
                    { validator: validateDuration, trigger: 'blur' }
                ],
            }
        };
    },

    methods: {
        dataRefreh() {
            // 计时器正在进行中，退出函数
            if (this.intervalId != null) {
                return;
            }
            // 计时器为空，操作
            this.intervalId = setInterval(() => {
                console.log("刷新" + new Date());
                this.initData(); //加载数据函数
            }, 5000);
        },
        format_date(value) {
            if (value) {
                return moment(String(value)).format('YYYY/MM/DD')
            }
        },

        handleClose() {
            if (this.dialogFormVisible == true) {
                // this.$refs['form'].resetFields();
                this.dialogFormVisible = false;

            } else {
                // this.$refs['form2'].resetFields();
                this.dialogFormVisible2 = false;
            }
        },

        addClass() {
            this.dialogFormVisible = true;
        },
        handleEdit(index) {

            this.form2.course_name = this.tableData[index].course_name
            this.form2.course_code = this.tableData[index].course_code
            this.form2.date = this.tableData[index].date
            this.form2.duration = this.tableData[index].duration.substring(0, this.tableData[index].duration.length - 1)
            this.form2.language = this.tableData[index].language
            this.form2.location = this.tableData[index].location
            this.form2.teacher = this.tableData[index].teacher
            this.form2.time = this.tableData[index].time
            this.form2.index = index

            this.dialogFormVisible2 = true

        },
        deleteClass(index) {

            this.tableData.splice(index, 1);

        },
        submitForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    // alert('submit!');
                    this.form.duration = this.form.duration + 'h';
                    this.form.date = this.format_date(this.form.date)
                    this.tableData.push(
                        JSON.parse(JSON.stringify(this.form))
                    );

                    console.log("hello world!");

                } else {
                    console.log('error submit!!');
                    return false;
                }
                this.$refs[formName].resetFields();
                this.dialogFormVisible = false;
            });
        },
        editForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    // alert('edit!');
                    this.form2.duration = this.form2.duration + 'h';
                    this.form2.date = this.format_date(this.form2.date)
                    this.tableData[this.form2.index] = JSON.parse(JSON.stringify(this.form2))
                    console.log(this.tableData[this.form2.index])
                    // 为了更新表单
                    this.num = Math.random();
                    console.log("hello world!");

                } else {
                    console.log('error submit!!');
                    return false;
                }
                this.$refs[formName].resetFields();
                this.dialogFormVisible2 = false;
            });
        },

        async resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible = false;
        },
        async resetForm2(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible2 = false;
        }
        ,
        setRowStyle(row) {
            if (row.row.isPart == true) {
                return 'color:#1772b4;'
            }
        },


    },
    watch: {
        $route(from) {
            window.location.reload(); //监测到路由发生跳转时刷新一次页面
            this.dataRefreh();

        },
    },

}
</script>


<style lang="scss">
#particle-canvas {
    width: 100vw;
    height: 100vh;
    background: linear-gradient(to bottom, rgb(10, 10, 50) 0%, rgb(60, 10, 60) 100%);
    vertical-align: middle;
}

.main {

    // background: linear-gradient(to bottom left, #3b818c 13%, #29b7cb 10%, #3b818c 80%, ),
    // linear-gradient(to left, rgba(255, 255, 255, 0.12) 0%, rgba(190, 65, 65, 0.12) 100%);
    background-blend-mode: screen;
    background-color: transparent; //这是设置透明背景色


    .header {
        font-family: 'nano';
        color: rgb(246, 116, 2);
        font-weight: bolder;
        background-color: transparent;
        line-height: 60px;
        text-align: center;
        font-size: 40px;
    }
}

.content {
    top: 100px;
    left: 0;
    //  display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    width: 100vw;
    height: 100vh;
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

Style Attribute {
    color: black;
    font-size: 14px;
    font-family: nano;
    background-color: rgb(0, 228, 211);
}

.el-button--primary {

    margin-top: 15px;
    height: 30px;
    line-height: 0.4;
    background-color: transparent;
    border: 1px solid rgb(236, 133, 14);
    border-radius: 1px;
    text-align: center;


    span {
        font-family: 'nano';
        color: white;
        font-size: 14px;
        letter-spacing: 1px;
    }
}

.el-table tbody tr:hover>td {
    background-color: #8dfae1;
}



.el-table {
    //background: linear-gradient(to bottom left, #3b818c 13%,#29b7cb 10%, #3b818c 80%, ),
    //  linear-gradient(to left, rgba(255, 255, 255, 0.12) 0%, rgba(190, 65, 65, 0.12) 100%);
    // background-blend-mode: screen;
    background-color: transparent; //这是设置透明背景色
    //text-align: center;
    padding: 0%;
}

// 表单样式start
form {
    display: block;
    background-color: transparent;
    margin-top: 0em;
    color: white;
}

.form_header {
    font-family: "nano";
    font-size: 20px;
    font-weight: bolder;
    text-align: center;
}

.el-input__inner {
    -webkit-appearance: none;
    background-color: transparent;
    border-radius: 6px;
    border: 1px solid #DCDFE6;
    box-sizing: border-box;
    color: white;
    display: inline-block;
    font-size: inherit;
    height: 40px;
    line-height: 40px;
    outline: 0;
    padding: 0 15px;
    transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
    width: 100%;
}

.button_div {

    text-align: center;
}

.el-dialog__body {
    padding: 30px 20px;
    color: white;
    background-color: transparent;
    font-size: 16px;
    background-image: url('@/assets/20221004004227.png');
    word-break: break-all;

}

.el-form-item__label {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 16px;
    color: white;
    line-height: 40px;
    padding: 0 12px 0 0;
    box-sizing: border-box;
}

.el-radio__label {
    padding-left: 10px;
    font-size: 16px;
    color: white;
}

.el-dialog__header {
    font-size: 22px;
    padding: 0%;
    color: white;
    background-image: url('@/assets/logo.png');

}

// 表单样式 end

.el-button--primary.is-plain:active {
    background: #10aec2;
    opacity: 30%;
    border-color: #3a8ee6;
    color: #10aec2;
    outline: 0;
}


.el-table--border .el-table__cell,
.el-table__body-wrapper .el-table--border.is-scrolling-left~.el-table__fixed {
    border-right: 1px solid #16d7cf;
}

.el-table .cell {
    background-color: transparent;
    color: black;
    opacity: 100%;
}

.el-table td.el-table__cell,
.el-table th.el-table__cell.is-leaf {
    border-bottom: 1px solid #1bd0bc;
}


.el-table--striped .el-table__body tr.el-table__row--striped td.el-table__cell {
    background: #b8f8fe;;
}

::v-deep .el-table tbody tr:hover>td {
    background-color: #8dfae1;
    opacity: 20%;
}

.el-table--enable-row-hover .el-table__body tr:hover>td.el-table__cell {
    background-color: #8dfae1;
}
.el-button--primary span {
    font-family: "nano";
    color: rgb(255, 255, 255);
    font-size: 14px;
    letter-spacing: 1px;
    }
.el-button--primary.is-plain {
    color: #409EFF;
    background: #225fa2;
    border-color: #b3d8ff;
}
.button_div {
    text-align: center;
    background-color: transparent;
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