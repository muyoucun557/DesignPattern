const objectPath = require('object-path')

function Config(Strategy) {
  if (this.constructor !== Config) {
    throw new Error(`Config structor can't invoke!`)
  }
  this.data = {}
  this.Strategy = Strategy
  this.flag = false

  this.get = function(path) {
    if (!this.flag) {
      throw new Error('尚未读取配置文件，请先读取配置文件')
    }
    return objectPath(this.data, path)
  }

  this.readFile = async function(path) {
    this.flag = true
    this.data = await Strategy.Deserialization(path)
  }

  this.saveFile = async function(path) {
    await Strategy.Serialization(path, JSON.stringify(this.data))
  }
}