<template>
  <div  style="display: flex;line-height: 60px">
    <div>
      <i :class="icon" style="font-size: 20px;line-height: 60px;cursor: pointer"
         @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align:center;font-size: 34px;">
      <span></span>
    </div>
    <el-dropdown>
      <span></span><i class="el-icon-user-solid" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name:"Header",
  methods:{
    toUser(){
      console.log('to_user')
    },
    logout(){
      console.log('log_out')
    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  props:{
    icon:String
  }
}
</script>
<style scoped>

</style>