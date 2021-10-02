<template>
		<!--表格显示列界面-->
		<el-dialog title="表格显示列" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
			<el-table ref="fitlerTable" :data="columns"  height="330px" tooltip-effect="dark" header-align="left" align="left"
				:size="size" style="width: 100%" @selection-change="selectionChange">
				<el-table-column type="selection" width="40">
				</el-table-column>
				<el-table-column label="属性">
					<template slot-scope="scope">{{ scope.row.prop }}</template>
				</el-table-column>
				<el-table-column label="列名">
					<template slot-scope="scope">
            <el-input :size="size" v-model="scope.row.label"></el-input>
          </template>
				</el-table-column>
				<el-table-column label="最小宽度">
					<template slot-scope="scope">
            <el-input :size="size" v-model="scope.row.minWidth"></el-input>
          </template>
				</el-table-column>
			</el-table>
			<div slot="footer" class="dialog-footer">
				<el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
				<el-button :size="size" type="primary" @click.native="handleFilterColumns">{{$t('action.comfirm')}}</el-button>
			</div>
		</el-dialog>
</template>

<script>
export default {
  name: 'TableColumnFilterDialog',
  components:{
  },
  props: {
    columns: {
      type: Array,
      default: []
    },
    size: {
      type: String,
      default: 'mini'
    }
  },
  data() {
    return {
      selections: [],  // 列表选中列
      dialogVisible: false
    }
  },
  methods: {
    // 选择切换
    selectionChange: function (selections) {
      this.selections = selections
      
    },
    // 设置可见性
    setDialogVisible: function (visible) {
      this.dialogVisible = visible
    },
		// 处理表格列过滤显示
    handleFilterColumns: function () {
      let filterColumns = []
      for(let i = 0; i <this.columns.length; i++) {
        let column = this.columns[i]
        if(this.hasColumn(column)) {
          filterColumns.push(column)
        }
      }
			this.$emit('handleFilterColumns', {filterColumns: JSON.parse(JSON.stringify(filterColumns))})
    },
    hasColumn: function (column) {
      for(let i = 0; i <this.selections.length; i++) {
        let col = this.selections[i]
        if(column.prop == col.prop) {
          return true
        }
      }
      return false
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>