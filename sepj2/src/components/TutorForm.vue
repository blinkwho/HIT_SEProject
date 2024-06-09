<template>
  <el-container>
    <el-header align="center"><h2>发布家教信息</h2></el-header>
    <el-main>
      <el-form :model="TutorInfoForm">
        <el-row align="middle">
          <el-col :span="8">
            <el-form-item prop ="telephoneNumber" label = "联系方式">
              <el-input v-model="TutorInfoForm.telephoneNumber"
                        style="margin-top: 20px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row align="middle">
          <el-col :span="100">
            <el-form-item prop = "startTime" label = "开始时间">
              <el-time-select
                  v-model="startTime"
                  :picker-options="{
                    start: '08:30',
                    step: '00:15',
                    end: '18:30'
                  }"
                  placeholder="选择开始时间"
                  style="margin-left: 1px">
              </el-time-select>
              <!--              <el-input v-model="StudentInfoForm.startDate"-->
              <!--                        style="margin-top: 20px"></el-input>-->
            </el-form-item>
          </el-col>
        </el-row>

        <el-row align="middle">
          <el-col :span="100">
            <el-form-item prop = "endTime" label = "终止时间">
              <el-time-select
                  v-model="endTime"
                  :picker-options="{
                    start: '08:30',
                    step: '00:15',
                    end: '18:30'
                  }"
                  placeholder="选择开始时间"
                  style="margin-left: 1px">
              </el-time-select>
              <!--              <el-input v-model="StudentInfoForm.startDate"-->
              <!--                        style="margin-top: 20px"></el-input>-->
            </el-form-item>
          </el-col>
        </el-row>

        <el-row align="middle">
          <el-col :span="8">
            <el-form-item prop = "date" label = "学习日期">
              <el-input v-model="date"
                        style="margin-top: 20px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row align="middle">
          <el-col :span="8">
            <el-form-item prop = "price" label = "期望薪资">
              <el-input v-model="TutorInfoForm.price"
                        style="margin-top: 20px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row align="middle">
          <el-col :span="11">
            <el-form-item prop = "subject" label = "学科">
              <el-radio-group v-model="TutorInfoForm.subject" style="margin-top: 50px">
                <el-radio label = "chinese" >语文</el-radio>

                <el-radio label = "math">数 学</el-radio>

                <el-radio label = "english">英 语</el-radio>

                <el-radio label = "physics">物 理</el-radio>

                <el-radio label = "chemistry">化 学</el-radio>

                <el-radio label = "biology">生 物</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row style="margin-top: 50px">
          <el-col :span="2" :offset="8">
            <el-button type="primary" style="width: 125px"
                       @click="submitTutorForm"><h2>提 交</h2></el-button>
          </el-col>
          <el-col :span="2" :offset="4">
            <el-button type="danger" style="width: 125px"><h2>取 消</h2></el-button>
          </el-col>
        </el-row>

      </el-form>

    </el-main>
  </el-container>
</template>

<script>
  export default {
    data(){
      return{
        startTime:"08:00:00",
        endTime:"18:30:00",
        date:"1970-01-01",
        TutorInfoForm:{
          subject: '',
          telephoneNumber: '',
          price: '',
          // startDate: '',
          // endDate: '',
          teacherId: '1'
        }
      }
    },
    methods:{
      submitTutorForm(){
        // post 方 法
        this.$axios.post('http://localhost:8090/teacher-post/insert', {
          subject:this.TutorInfoForm.subject,
          telephoneNumber:this.TutorInfoForm.telephoneNumber,
          price:this.TutorInfoForm.price,
          startDate:this.date + 'T' + this.startTime,
          endDate:this.date + 'T' + this.endTime,
          teacherId:this.TutorInfoForm.teacherId
        }).then(response=>response.data).then(response=> {
          if (response.code == 200) {
            this.$message({
              message: "成功添加",
              type: "success"
            })
          } else {
            this.$message({
              message: "失败",
              type: "error",
            })
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>