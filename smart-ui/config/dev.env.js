'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_URL: '"http://127.0.0.1:7778"',
  OAUTH_SERVE_URL: '"http://127.0.0.1:7775/oauth"'
})
