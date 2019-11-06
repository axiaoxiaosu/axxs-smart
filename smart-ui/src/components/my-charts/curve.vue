
<template>
  <div id="curve" ref="curve" style="width: 90%;height: 330px;" />
</template>

<script>
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/line' // 引入线图
import 'echarts/lib/chart/bar' // 引入柱状图
import 'echarts/lib/chart/pie' // 引入环形图
import 'echarts/lib/component/tooltip' // 引入提示框组件
import 'echarts/lib/component/legend' // 引入图例组件
import 'echarts/lib/component/dataZoom'

export default {
  name: 'Curve',
  data() {
    return {
      charts: {
        xAxisData: [],
        data1: [],
        data2: []
      }
    }
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart() {
      const _self = this
      const myChart = echarts.init(_self.$refs.curve)

      for (var i = 0; i < 100; i++) {
        _self.charts.xAxisData.push('类目' + i)
        _self.charts.data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5)
        _self.charts.data2.push((Math.cos(i / 5) * (i / 5 - 10) + i / 6) * 5)
      }

      const option = {
        title: {
          text: '柱状图动画延迟'
        },
        legend: {
          data: ['bar', 'bar2'],
          align: 'left'
        },
        toolbox: {
          // y: 'bottom',
          feature: {
            magicType: {
              type: ['stack', 'tiled']
            },
            dataView: {},
            saveAsImage: {
              pixelRatio: 2
            }
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        xAxis: {
          data: _self.charts.xAxisData,
          silent: false,
          splitLine: {
            show: false
          }
        },
        yAxis: {
        },
        series: [{
          name: 'bar',
          type: 'bar',
          itemStyle: {
            normal: { color: '#42A5F5' }
          },
          data: _self.charts.data1,
          animationDelay: function(idx) {
            return idx * 10
          }
        }, {
          name: 'bar2',
          type: 'bar',
          itemStyle: {
            normal: { color: '#48C9B0' }
          },
          data: _self.charts.data2,
          animationDelay: function(idx) {
            return idx * 10 + 100
          }
        }],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function(idx) {
          return idx * 5
        }
      }
      myChart.setOption(option)
    }
  }
}
</script>

<style scoped>
</style>

