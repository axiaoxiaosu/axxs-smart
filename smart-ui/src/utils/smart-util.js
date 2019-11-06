const myIsNotEmpty = (e) => {
  if (e === null) {
    return false
  } else if (typeof (e) === 'undefined') {
    return false
  } else if (e === 'null' || e.length < 1) {
    return false
  }
  return true
}

// 深度copy
const copy = (obj) => {
  var o
  // 如果  他是对象object的话  , 因为null,object,array  也是'object';
  if (typeof obj === 'object') {
    // 如果  他是空的话
    if (obj === null) {
      o = null
    } else {
      // 如果  他是数组arr的话
      if (obj instanceof Array) {
        o = []
        for (var i = 0, len = obj.length; i < len; i++) {
          o.push(copy(obj[ i ]))
        }
      } else {
        // 如果  他是对象object的话
        o = {}
        for (var j in obj) {
          o[ j ] = copy(obj[ j ])
        }
      }
    }
  } else {
    o = obj
  }
  return o
}

const treeDispose = (data, c = 0, level = 0, id = 'id', pid = 'parentId') => {
  const ary = []
  level++
  data.filter(e => {
    if (e.parentId === c) {
      ary.push(e)
    }
  })
  for (let i = 0; i < ary.length; i++) {
    ary[i].children = treeDispose(data, ary[i].id, level)
    ary[i]._level = level
    ary[i].label = ary[i].title
  }
  return ary
}

export default {
  myIsNotEmpty: myIsNotEmpty,
  copy: copy,
  treeDispose: treeDispose
}

