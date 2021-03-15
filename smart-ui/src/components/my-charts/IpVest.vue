
<template>
  <div>
    <div ref="ip" class="echarts-layout"/>
    <div style="margin: auto 0;text-align: center" >
      <span class="title-font">访问情况统计</span>
    </div>
  </div>
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
  name: 'IpVest',
  props: {
    chartData: {
      type: Object,
      default: function() {
        return {
          dataAxis: ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'],
          data: [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220],
          yMax: 800,
          dataShadow: []
        }
      }
    }
  },
  data() {
    return {
      chart: {}
    }
  },
  watch: {
    chartData(newVal, oldVal) {
      if (this.chartData) {
        if (newVal) {
          this.initChart(newVal)
        } else {
          this.initChart(oldVal)
        }
      }
    }
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart(param) {
      const _self = this
      if (!param) {
        param = _self.chartData
      }
      _self.chart = echarts.init(_self.$refs.ip)
      for (let i = 0; i < param.data.length; i++) {
        param.dataShadow.push(param.yMax)
      }
      const option = {
        title: {
          text: '特性示例：渐变色 阴影 点击缩放',
          subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
        },
        xAxis: {
          data: param.dataAxis,
          axisLabel: {
            inside: true,
            textStyle: {
              color: '#fff'
            },
            formatter: function(value) {
              return value.split('').join('\n')
            }
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          z: 10
        },
        yAxis: {
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#999'
            }
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: (e) => {
            return '<div>' + e[1].name + ':' + e[1].data + '</div>'
          }
        },

        dataZoom: [
          {
            type: 'inside'
          }
        ],
        series: [
          { // For shadow
            type: 'bar',
            itemStyle: {
              normal: { color: 'rgba(0,0,0,0.05)' }
            },
            barGap: '-100%',
            barCategoryGap: '40%',
            data: param.dataShadow,
            animation: false
          },
          {
            type: 'bar',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: '#83bff6' },
                    { offset: 0.5, color: '#188df0' },
                    { offset: 1, color: '#188df0' }
                  ]
                )
              },
              emphasis: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: '#2378f7' },
                    { offset: 0.7, color: '#2378f7' },
                    { offset: 1, color: '#83bff6' }
                  ]
                )
              }
            },
            data: param.data
          }
        ]
      }
      const zoomSize = 6
      _self.chart.on('click', function(params) {
        _self.chart.dispatchAction({
          type: 'dataZoom',
          startValue: param.dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue: param.dataAxis[Math.min(params.dataIndex + zoomSize / 2, param.data.length - 1)]
        })
      })
      _self.chart.setOption(option)
    }

  }
}
</script>

<style scoped>
  .title-font{
    font-family: "20px Extra large";
  }
  .echarts-layout{
    height: 40vh;
    width: 50vw;
  }
</style>

