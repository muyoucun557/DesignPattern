// 序列化：把对象转成字节序列
// 反序列化：把字节序列转成对象
const fsPromise = require('fs').promises

const json = {
  Deserialization: function(path) {
    return require(path)
  },
  Serialization: function(path, text) {
    await fsPromise.writeFile(path, text)
  }
}

module.exports = {
  json
}