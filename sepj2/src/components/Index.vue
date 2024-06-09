<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="asideWidth" style="background-color: rgb(238, 241, 246);margin-left: -1px;margin-top: -1px;margin-bottom: -3px">
      <Aside :isCollapse = "isCollapse" @conveyCategory = "conveyCategory"></Aside>
    </el-aside>

    <el-container style="height: 100%">
      <el-header style="text-align: right; font-size: 12px;border-bottom: lightgray 1px solid">
        <Header @doCollapse = "doCollapse" :icon = "icon"></Header>
      </el-header>

      <el-main v-if="selected_category === 1">

          </el-main>
      <el-main v-if="selected_category === 2">

          </el-main>
      <el-main v-if="selected_category === 3">

          </el-main>
      <el-main v-if="selected_category === 4">

          </el-main>
      <el-main v-if="selected_category === 5">

          </el-main>
      <el-main v-if="selected_category === 6">

          </el-main>


      <el-main v-if="selected_category === 7" style="background-color: blue">

        </el-main>
      <el-main v-if="selected_category === 8" style="background-color: blue">

        </el-main>

      <el-main v-if="selected_category === 9">
        <TutorForm></TutorForm>
      </el-main>

      <el-main v-if="selected_category === 10">
        <StudentForm></StudentForm>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import TutorForm from "@/components/TutorForm.vue"
import StudentForm from "@/components/StudentForm.vue";
  export default {
    name: "Index",
    components: {Header, Aside, TutorForm,StudentForm},
    data() {
      return {
        isCollapse: false,
        asideWidth: '200px',
        icon: 'el-icon-arrow-left',
        selected_category: 9
      }
    },
    methods: {
      doCollapse() {
        console.log("yes")
        this.isCollapse = !this.isCollapse
        if (this.isCollapse) {//收起
          this.asideWidth = '1px'
          this.icon = 'el-icon-arrow-right'
        } else {//展开
          this.asideWidth = '200px'
          this.icon = 'el-icon-arrow-left'
        }
      },
      conveyCategory(category) {
        switch (category) {
          case 'Class A':
            this.selected_category = 1
            break;
          case 'Class B':
            this.selected_category = 2;
            break;
          case 'Class C':
            this.selected_category = 3;
            break;
          case 'Class D':
            this.selected_category = 4;
            break;
          case 'Class E':
            this.selected_category = 5;
            break;
          case 'Class F':
            this.selected_category = 6;
            break;

          case 'Sort A':
            this.selected_category = 7;
            break;
          case 'Sort B':
            this.selected_category = 8;
            break;

          case 'Info A':
            this.selected_category = 9;
            break;
          case 'Info B':
            this.selected_category = 10;
            break;
        }
      },
    }
  }
</script>

<style scoped>

</style>