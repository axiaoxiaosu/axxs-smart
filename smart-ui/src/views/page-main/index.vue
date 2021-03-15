<template>
  <div class="page" style="width: 100%; height:85vh">
    <el-row style="height: 10%">
      <el-col :span="24">
        <el-card
          style="text-align: center;">
          <label class="title">
            <i class="fa fa-tree fa-1x" style="color: #a0eee1;"/>有些人，一旦遇见，便一眼万年；有些心动，一旦开始，便覆水难收
            <i class="fa fa-tree fa-1x" style="color: #a0eee1;"/>
          </label>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="10" style="height: 45% ">
      <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8" style="height: 100%">
        <el-row>
          <el-card id="test-1" :body-style="{ height: '29vh', margin:'30px'}" >
            <SmartBanner :slide="imgArray" />
          </el-card>
        </el-row>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16" style="height: 100%">
        <el-card style="height: 100%;">
          <IpVest :chart-data="ipChartData" />
        </el-card>
      </el-col>
    </el-row>
    <el-row >
      <el-col :span="24">

        <el-card>
          <Curve/>
        </el-card>

      </el-col>
    </el-row>
  </div>

</template>

<script>

import SmartBanner from '@/components/swiper/SmartBanner.vue'
import IpVest from '@/components/my-charts/IpVest.vue'
import Curve from '@/components/my-charts/curve'
import { getIp } from '@/api/report.js'

// 局部引入
export default {
  name: 'AppMain',
  components: { SmartBanner, IpVest, Curve },
  data() {
    return {
      ipChartData: {
        dataAxis: [],
        data: [],
        yMax: 10,
        dataShadow: []
      },
      imgArray: [
        'https://cdn.jsdelivr.net/gh/axiaoxiaosu/imgResource@main/snow/VHlBKzCuYbXUfNR.jpg',
        'https://cdn.jsdelivr.net/gh/axiaoxiaosu/imgResource@main/snow/82884208_p0_master1200.jpg',
        'https://cdn.jsdelivr.net/gh/axiaoxiaosu/imgResource@main/snow/76508324_p0_master1200.jpg'
      ]
    }
  },
  mounted() {
    const _self = this
    _self.initChartsIp()
  },
  methods: {
    initChartsIp() {
      const _self = this
      getIp().then((e) => {
        if (e.data) {
          const result = {
            dataAxis: [],
            data: [],
            yMax: 10,
            dataShadow: []
          }
          for (let i = 0; i < e.data.length; i++) {
            result.dataAxis.push(e.data[i].address)
            result.data.push(e.data[i].count)
          }
          result.yMax = e.data[0].count
          _self.ipChartData = result
        }
      })
        .catch()
    }

  }

}
</script>

<style scoped>
  .title {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  .ipInfo{
    height: 40%;
    padding: 10px 0;
  }
</style>
