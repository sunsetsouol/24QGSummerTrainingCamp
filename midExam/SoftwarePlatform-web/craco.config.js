const path = require("path"); // 引入 Node.js 的 path 模块，用于处理和转换文件路径。

const CracoLessPlugin = require("craco-less"); // 引入 craco-less 插件，用于配置 Less。

module.exports = {
  webpack: {
    // webpack 配置部分
    alias: {
      // 配置路径别名
      "@": path.resolve(__dirname, "src"), // 将 '@' 映射到项目根目录下的 'src' 文件夹。
    },
  },
  babel: {
    // babel 配置部分
    plugins: [
      ["import", { libraryName: "antd", libraryDirectory: "es", style: true }], // 使用 babel-plugin-import 插件按需加载 antd 组件和样式。
    ],
  },
  plugins: [
    // craco 插件配置部分
    {
      plugin: CracoLessPlugin, // 使用 craco-less 插件配置 Less
      options: {
        lessLoaderOptions: {
          // 配置 Less 加载器选项
          lessOptions: {
            modifyVars: { "@primary-color": "#1DA57A" }, // 自定义主题颜色，将 antd 的主颜色修改为 '#1DA57A'。
            javascriptEnabled: true, // 启用 Less 中的 JavaScript。
          },
        },
      },
    },
  ],
};
