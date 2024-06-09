<template>
  <el-menu style = "height: 100%;"
           background-color="#fff"
           text-color="373737"
           active-text-color="#2440b3"
           default-active="3-1"
           :collapse = "isCollapse"
           :collapse-transition = "true">
    <el-submenu index="1">

      <template slot = "title">
        <span style="font-size: 17px;margin-left: 25px"> 课 程 分 类 </span>
      </template>

      <el-menu-item-group>
        <el-menu-item @click="selectCategory('Class A')" index="1-1">
          <span style="font-size: 15px; margin-left: 30px"> 语 文</span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Class B')" index="1-2">
          <span style="font-size: 15px; margin-left: 30px">数 学</span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Class C')" index="1-3">
          <span style="font-size: 15px; margin-left: 30px">英 语</span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Class D')" index="1-4">
          <span style="font-size: 15px; margin-left: 30px">物 理</span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Class E')" index="1-5">
          <span style="font-size: 15px; margin-left: 30px">化 学</span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Class F')" index="1-6">
          <span style="font-size: 15px; margin-left: 30px">生 物</span>
        </el-menu-item>
      </el-menu-item-group>

    </el-submenu>

    <el-submenu index="2">
      <template slot="title">
        <span style="font-size: 17px; margin-left: 50px"> 排 序 </span>
      </template>

      <el-menu-item-group>
        <el-menu-item @click="selectCategory('Sort A')" index="2-1">
          <span style="font-size: 15px; margin-left: 10px"> 按 价 格 排 序 </span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Sort B')" index="2-2">
          <span style="font-size: 15px; margin-left: 10px"> 按 时 间 排 序 </span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>

    <el-submenu index="3">
      <template slot="title">
        <span style="font-size: 17px; margin-left: 25px"> 发 布 需 求 </span>
      </template>

      <el-menu-item-group>
        <el-menu-item @click="selectCategory('Info A')" index="3-1">
          <span style="font-size: 15px; margin-left: 10px"> 发 布 家 教 需 求 </span>
        </el-menu-item>
        <el-menu-item @click="selectCategory('Info B')" index="3-2">
          <span style="font-size: 15px; margin-left: 10px"> 发 布 学 生 信 息 </span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default{
  name:"Aside",
  data(){
    return{
    }
  },
  props:{
    isCollapse:Boolean
  },
  methods:{
    selectCategory(category){
      console.log(category)
      this.$emit('conveyCategory',category)
    }
  }
}
</script>

<style scoped>

</style>