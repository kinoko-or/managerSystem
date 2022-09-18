<template>
  <el-container style="min-height: 100vh">
    <el-aside :width=" LeftSideWidth + 'px'" style="background-color: rgb(238, 241, 246);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x: hidden; border-right: 0;"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; text-align: center; line-height: 60px">
          <img src="../assets/logo.png" style="width: 18px; position: relative; top: 5px;"/>
          <b style="color: white; margin-left: 5px;" v-show="!isCollapse">后台管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span>导航一</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>导航二</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>导航三</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 25px;">
          <span :class="collapseBtnClassName" style="cursor: pointer" @click="collapseLeftItem"></span>
        </div>
        <el-dropdown style="text-align: right;">
          <span style="cursor: pointer">
            <span>王小虎</span><i class="el-icon-bottom" style="margin-left: 5px"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>

        <div style="margin-top: 10px">
          <el-input v-model="username" class="searchInput" suffix-icon="el-icon-search" placeholder="名称"></el-input>
          <el-input v-model="email" class="searchInput" suffix-icon="el-icon-message" placeholder="邮箱"></el-input>
          <el-input v-model="address" class="searchInput" suffix-icon="el-icon-position" placeholder="地址"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
          <el-button type="warning" icon="el-icon-refresh-right" @click="reset">重置</el-button>
        </div>

        <div style="margin-top: 10px">
          <el-button type="primary" @click="handleAddUser()">添加 <i class="el-icon-circle-plus-outline"></i>
          </el-button>
          <el-popconfirm
              title="是否确定删除？"
              @confirm="deleteByBatchId"
              class="ml_5"
          >
            <el-button slot="reference" type="danger">批量删除 <i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
          <el-button type="primary" class="ml_5">导入 <i class="el-icon-bottom"></i></el-button>
          <el-button type="primary" class="ml_5">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <!--添加用户表单-->
        <el-dialog title="添加用户" :visible.sync="addDialogFormVisible">
          <el-form :model="newUser">
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="newUser.username" autocomplete="off"></el-input>
            </el-form-item>
            <!--<el-form-item label="密码" :label-width="formLabelWidth">-->
            <!--  <el-input v-model="newUser.password" autocomplete="off"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="昵称" :label-width="formLabelWidth">
              <el-input v-model="newUser.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="newUser.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input v-model="newUser.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-select v-model="newUser.address" placeholder="请选择区域">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="noAddUser()">取 消</el-button>
            <el-button type="primary" @click="addUserForm()">确 定</el-button>
          </div>
        </el-dialog>

        <!--修改用户表单-->
        <el-dialog title="编辑用户" :visible.sync="editDialogFormVisible">
          <el-form :model="editUser">
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="editUser.username" autocomplete="off"></el-input>
            </el-form-item>
            <!--<el-form-item label="密码" :label-width="formLabelWidth">-->
            <!--  <el-input v-model="editUser.password" autocomplete="off"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="昵称" :label-width="formLabelWidth">
              <el-input v-model="editUser.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="editUser.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input v-model="editUser.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-select v-model="editUser.address" placeholder="请选择区域">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="noEditUser()">取 消</el-button>
            <el-button type="primary" @click="editUserForm()">确 定</el-button>
          </div>
        </el-dialog>

        <el-table border stripe height="619" style="margin-top: 10px" :data="tableData"
                  :header-cell-class-name="table_css"
                  @selection-change="handleSelectionChange">
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column prop="id" label="ID" width="60"></el-table-column>
          <el-table-column prop="username" label="用户名" width="140"></el-table-column>
          <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
          <el-table-column prop="email" label="邮箱"></el-table-column>
          <el-table-column prop="phone" label="电话"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="small" type="primary" @click="handleEditUser(scope.row)">编辑 <i class="el-icon-edit"></i>
              </el-button>
              <!--删除确认框-->
              <el-popconfirm
                  title="是否确定删除？"
                  @confirm="deleteUser(scope.row.id)"
              >
                <el-button slot="reference" size="small" type="danger">删除 <i
                    class="el-icon-delete"></i></el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>

        <div style="width: 100%; text-align: left; margin-top: 10px" class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import axios from "axios";

export default {
  name: 'HomeView',
  data() {
    return {
      //侧边栏图标
      collapseBtnClassName: "el-icon-s-fold",
      //是否显示侧边栏图标
      isCollapse: false,
      //侧边栏边距
      LeftSideWidth: 200,
      //表单数据
      tableData: [],
      //表单数据总数
      total: 0,
      //当前页码
      pageNum: 1,
      //每页数据条数
      pageSize: 5,
      table_css: "table_css",
      username: "",
      email: "",
      address: "",
      newUser: {
        username: "",
        // password:"",
        nickname: "",
        phone: "",
        email: "",
        address: "",
      },

      editUser: {
        username: "",
        // password:"",
        nickname: "",
        phone: "",
        email: "",
        address: "",
      },

      formLabelWidth: "120px",
      selections: [],


      addDialogFormVisible: false,
      editDialogFormVisible: false,
      delDialogVisible: false,
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.loadTable()
    },
    loadTable() {
      let str = "";
      //封装分页刷新
      this.request.get("/user/selectByCondition/", {
        params: {
          username: this.username,
          email: this.email,
          address: this.address,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }).then(res => {
        this.total = res.total
        this.tableData = res.data
      })
    },
    addUser() {
      this.request.post("/user/insertUser/", this.newUser).then(res => {
        if (res) {
          this.$message.success("添加成功！")
          this.addDialogFormVisible = false
          this.loadTable()
        } else {
          this.$message.error("请检查数值合法性")
          this.loadTable()
        }
      })
    },
    updateUser() {
      this.request.put("/user/updateUser/", this.editUser).then(res => {
        if (res) {
          this.$message.success("修改成功！")
          this.editDialogFormVisible = false
          this.loadTable()
        } else {
          this.$message.error("请检查数值合法性")
          this.loadTable()
        }
      })
    },
    deleteUser(id) {
      this.request.delete("/user/deleteUser/" + id).then(res => {
        if (res) {
          this.$message.success("删除成功！")
          this.loadTable()
        } else {
          this.$message.fail("删除失败！")
          this.loadTable()
        }
      })
    },
    deleteByBatchId() {
      if (this.selections.length === 0) {
        this.$message.error("请勾选需要删除的数据！")
        return;
      }
      let deleteId = this.selections.map(selection => selection.id)
      console.log(deleteId)
      this.request.delete("/user/deleteUser/batch/", {
        data: deleteId
      }).then(res => {
        if (res) {
          this.$message.success("删除成功！")
        } else {
          this.$message.error("删除失败！")
        }
      })
      this.loadTable()
    },

    handleEditUser(row) {
      this.editDialogFormVisible = true
      //复制一份row，避免未提交数据表格就发生更改的情况
      this.editUser = Object.assign(this.editUser, row)
    },
    editUserForm() {
      for (let temp in this.editUser) {
        if (this.editUser[temp] == null || this.editUser[temp] == "") {
          this.$message.error("请检查" + temp + "合法性")
          // console.log(temp)
          return false
        }
      }
      this.updateUser()
    },
    noEditUser() {
      this.editDialogFormVisible = false
    },
    collapseLeftItem() {
      //控制左边item的收缩，同时修改收缩按钮的图标
      this.isCollapse = !this.isCollapse
      this.collapseBtnClassName = this.isCollapse ? "el-icon-s-unfold" : "el-icon-s-fold"
      this.LeftSideWidth = this.isCollapse ? "64" : "200"
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.loadTable()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.loadTable()
    },
    search() {
      this.loadTable()
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.loadTable()
    },
    handleAddUser() {
      this.addDialogFormVisible = true
    },
    addUserForm() {
      for (let temp in this.newUser) {
        if (this.newUser[temp] == null || this.newUser[temp] == "") {
          this.$message.error("请检查" + temp + "合法性")
          // console.log(temp)
          return false
        }
      }
      this.addUser()
    },
    noAddUser() {
      for (let newUserKey in this.newUser) {
        this.newUser[newUserKey] = ""
      }
      this.addDialogFormVisible = false
    },
    handleSelectionChange(val) {
      this.selections = val
    }
  }
}
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.table_css {
  background-color: #ececec !important;
}

.ml_5 {
  margin-left: 5px !important;
}

</style>
