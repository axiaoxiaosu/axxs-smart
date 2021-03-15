<template>
  <div>
    <div class="top-tiltle">
      <el-card>
        <div class="top-tiltle-info">
          <div>
            <i class="fa fa-tree fa-1x" style="color: #a0eee1;"/>有些人，一旦遇见，便一眼万年；有些心动，一旦开始，便覆水难收
            <i class="fa fa-tree fa-1x" style="color: #a0eee1;"/>
          </div>
        </div>
      </el-card>
    </div>
    <div class="top-layout">
      <div class="wuming">
        <el-card :body-style="{height:'50vh',width:'100%'}">
          <SmartBanner :slide="imgArray" />
        </el-card>
      </div>
      <div class="ip-info">
        <el-card :body-style="{height:'50vh',width:'100%'}">
          <IpVest :chart-data="ipChartData" />
        </el-card>
      </div>
    </div>
    <div class="bottom-layout">
      <el-card>
        <Curve/>
      </el-card>
    </div>

  </div>
</template>
<script>
import SmartBanner from '@/components/swiper/SmartBanner.vue'
import IpVest from '@/components/my-charts/IpVest.vue'
import Curve from '@/components/my-charts/curve'
import { getIp } from '@/api/report.js'

// 局部引入
export default {
  name: 'Home',
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
      getIp()
        .then((e) => {
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
<style rel="stylesheet/scss" lang="scss" scoped >
@import "src/styles/css/home.scss";
</style>

