import React from "react";
import { DownOutlined } from "@ant-design/icons";
import { Dropdown, Space } from "antd";
import "./Dropdown.css"; // 引入你的 CSS 文件
import { NavLink } from "react-router-dom";
const items = [
  {
    key: "1",
    label: <NavLink to={"/header/manageChecked"}>上架库</NavLink>,
  },
  {
    key: "2",
    label: <NavLink to={"/header/manageUnchecked"}>产品库</NavLink>,
  },
];
const ManageDropDown = () => (
  <Dropdown
    menu={{
      items,
    }}
    overlayClassName="custom-dropdown" // 添加自定义类名
  >
    <a onClick={(e) => e.preventDefault()}>
      <Space>
        管理
        <DownOutlined />
      </Space>
    </a>
  </Dropdown>
);
export default ManageDropDown;
