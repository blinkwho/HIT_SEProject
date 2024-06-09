<template>
  <div class="registerBody">
    <div class="registerDiv">
      <div class="register-content">
        <h1 class="register-title">用户注册</h1>
        <el-form :model="registerForm" label-width="100px"
                 :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="name">
            <el-input style="width: 200px" type="text" v-model="registerForm.name"
                      autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input style="width: 200px" type="password" v-model="registerForm.password"
                      show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
          </el-form-item>
          <el-form-item label="电话号" prop="telephoneNumber">
            <el-input style="width: 200px" type="telephoneNumber" v-model="registerForm.tel"
                      autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
          </el-form-item>
          <el-form-item label="用户身份" prop="roleId">
            <el-radio v-model="registerForm.roleId" label="1">教师</el-radio>
            <el-radio v-model="registerForm.roleId" label="2">学生</el-radio>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确 定</el-button>
            <el-button type="primary" style="margin-left: 30px" @click="login">返 回</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "Login",
  data(){
    return{
      confirm_disabled:false,
      registerForm:{
        name:'',
        password:'',
        roleId:'',
        tel:''
      },
      rules:{
        name: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输密码', trigger: 'blur' }
        ],
        roleId: [
          { required: true, message: '请输用户名', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    login(){
      this.$router.replace('/');
    },
    confirm(){
      this.confirm_disabled=true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) { //valid成功为true，失败为false
          //去后台验证用户名密码
          this.$axios.post(this.$urlHost+'/user/insert',this.registerForm).then(res=>res.data).then(res=>{
            console.log(res)
            if(res.code==200){
              //存储
              // console.log(res.data.menu)
              // this.$store.commit("setMenu",res.data.menu)
              //跳转到主页
              alert('成功！');
              this.$router.replace('/');
            }else{
              this.confirm_disabled=false;
              alert('校验失败，用户名或密码错误！');
              return false;
            }
          });
        } else {
          this.confirm_disabled=false;
          console.log('校验失败');
          return false;
        }
      });

    }
  }
}
</script>

<style scoped>
.registerBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #B3C0D1;
}
.registerDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 400px;
  background: #fff;
  border-radius: 5%;

}
.register-title {
  margin: 20px 0;
  text-align: center;
}
.register-content {
  width: 400px;
  height: 500px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>