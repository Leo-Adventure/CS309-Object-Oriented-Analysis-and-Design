<template>
    <div class="main">
      <!-- 背景画布 -->
      <canvas id="particle-canvas" width="2232" height="608"></canvas>
      <div class='content'>
  
        <div class="login">
          <h4 class="title">Course Selection System</h4>
          <el-form size="mini" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px"
            class="demo-ruleForm">
            <el-form-item label="username" prop="loginId">
              <el-input v-model="ruleForm.loginId" autocomplete="off"></el-input>
            </el-form-item>
  
            <el-form-item label="password" prop="loginPwd">
              <el-input type="password" v-model="ruleForm.loginPwd" autocomplete="off"></el-input>
            </el-form-item>
           
            <el-form-item class="item_button">
              <el-button type="primary" @click="submitForm('ruleForm')">login</el-button>
              <el-button @click="resetForm('ruleForm')">cancel</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
  
  
    </div>
  </template>
  
  <meta charset=​"UTF-8">​
  <script>
  import { strToMd5 } from '../util/md5'
  import { start } from '@/assets/js/login';
  export default {
    // DOM 加载完毕
    mounted() {
      start()
    },
    data() {
      var validateLoginId = (rule, value, callback) => {
        if (value == '') {
          callback(new Error('Please enter username'));
        } else {
          callback();
        }
      }
      //验证密码
      var validateLoginPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter password'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
  
      return {
        // 表单数据
        ruleForm: {
          loginId: '',
          loginPwd: '',
          checkMe: false
        },
        rules: {
          // 验证登录名
          loginId: [
            { validator: validateLoginId, trigger: 'blur' }
          ],
          // 验证密码
          loginPwd: [
            { validator: validateLoginPwd, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      
      submitForm(formName) {
        // console.log(formName);
        this.$refs[formName].validate(async (valid) => {
          if (valid) {
            // 对密码进行加密
            this.ruleForm.loginPwd = strToMd5(this.ruleForm.loginPwd);
            let { message, success } = await this.$get('Admin/Login', this.ruleForm);
            if (success) {
              // 服务器会返回令牌，保存身份信息，通过 token 去控制权限
              // 所以需要保存该token 信息，通常保存在浏览器的缓存空间当中
              // 浏览器的缓存空间有两种，local-storage 保存的数据会一直存在，session-storage 保存的数据随着浏览器的关闭而自动清空
              sessionStorage.setItem('token', success);
              
              console.log("successful log in")
              // 将token 信息放到请求当中
              this.$setToken()
              // 要跳转到后台管理 push
              this.$router.push('/layout')
  
            }
            
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
  #particle-canvas {
    width: 100vw;
    height: 100vh;
    background: linear-gradient(to bottom, rgb(10, 10, 50) 0%, rgb(60, 10, 60) 100%);
    vertical-align: middle;
  }
  
  .el-button--primary{
    height: 30px;
    line-height: 0.4;
    background-color: rgb(236, 133, 14);
    border: 1px solid rgb(236, 133, 14);
    border-radius: 1px;
    span{
      font-family: 'nano';
      color: #fff;
      font-size: 14px;
      letter-spacing: 1px;
    }
  }
  
  
  .el-button{
    height: 30px;
    line-height: 0.4;
    position: center;
    span{
      font-family: 'nano';
      font-size: 14px;
      letter-spacing: 1px;
    }
  }
  
  .el-form-item__label{
    color: white;
  }
  
  
  
  
  .main {
    width: 100vw;
    height: 100vh;
    position: relative;
  
    .content {
      top: 0;
      left: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      position: absolute;
      width: 100vw;
      height: 100vh;
  
      .login {
        font-family: nano;
        
        width: 460px;
        height: 200px;
        border: 1px solid #ccc;
        padding: 20px;
        border-radius: 10px;
  
        .title {
          font-size: 18px;
          text-align: center;
          color: white;
          margin-bottom: 20px;
        }
      }
    }
  
  }
  </style>