module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8081', //测试使用
                //target: 'http://47.92.202.164:8443', //正式
                changeOrigin: true, // 是否允许跨越, 开发环境中使用
                pathRewrite: {
                    // '^/api': '/mock',//测试使用
                    '^/api': ''
                }
            }
        }
    },
}