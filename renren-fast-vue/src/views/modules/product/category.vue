<template>
  <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick">
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <=2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >Append</el-button>
          <el-button type="text" size="mini" @click="edit(data)">edit</el-button>
          <el-button
            v-if="node.childNodes.length==0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >Delete</el-button>
        </span>
      </span>
  </el-tree>
</template>

<script>
export default {
  name: 'category',
  data () {
    return {
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  methods: {
    handleNodeClick (data) {
      console.log(data)
    },
    // 获取菜单列表
    getMenus () {
      this.$http({
        // 请求商品服务的分类数据
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log('成功获取到菜单数据...', data.data)
        // 菜单数据绑定
        this.menus = data.data
      })
    },
    // 编辑菜单
    edit (data) {
      console.log('要修改的数据', data)
      this.dialogType = 'edit'
      this.title = '修改分类'
      this.dialogVisible = true

      // 发送请求获取当前节点最新的数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({data}) => {
        // 请求成功
        console.log('要回显的数据', data)
        this.category.name = data.data.name
        this.category.catId = data.data.catId
        this.category.icon = data.data.icon
        this.category.productUnit = data.data.productUnit
        this.category.parentCid = data.data.parentCid
        this.category.catLevel = data.data.catLevel
        this.category.sort = data.data.sort
        this.category.showStatus = data.data.showStatus
        /**
         *         parentCid: 0,
         catLevel: 0,
         showStatus: 1,
         sort: 0,
         */
      })
    }
  },
  //
  created () {
    this.getMenus()
  }
}
</script>

<style scoped>

</style>
