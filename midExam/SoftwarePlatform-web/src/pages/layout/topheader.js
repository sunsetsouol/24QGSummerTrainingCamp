import "./layout.css";
import { Layout, Menu, Input, Button } from "antd";
import { NavLink } from "react-router-dom";
import { useEffect, useState } from "react";
import StoreDropDown from "./Dropdown";
import ManageDropDown from "./manageDropdown";
import { useAdmin } from "../../utils/context"; // 导入自定义 Hook
const { Header } = Layout;
const TopHeader = () => {
  const [loginState, setLoginStage] = useState(0);
  const { isAdmin } = useAdmin();
  useEffect(() => {
    if (localStorage.getItem("token") && localStorage.getItem("userIdSf"))
      setLoginStage(1);
    else setLoginStage(0);
  }, []);

  return (
    <Header className="top_head">
      <div className="top_title">
        <h3>工业软件管理平台</h3>
      </div>
      <div className="top_nav">
        <Menu className="top_menu">
          <NavLink to={"/header/home"}>
            <Menu.Item className="top_menu_item">首页</Menu.Item>
          </NavLink>
          <Menu.Item className="top_menu_item">
            <StoreDropDown />
          </Menu.Item>
          <NavLink to={"/header/person"}>
            <Menu.Item className="top_menu_item">我的</Menu.Item>
          </NavLink>
          <NavLink to={"/header/about"}>
            <Menu.Item className="top_menu_item">关于我们</Menu.Item>
          </NavLink>
          <Menu.Item
            className="top_menu_item"
            style={isAdmin ? { display: "block" } : { display: "none" }}
          >
            <ManageDropDown />
          </Menu.Item>
        </Menu>
      </div>
      <div className="top_search">
        <Input.Search className="top_input" placeholder="搜索"></Input.Search>
      </div>
      <div className="top_btn">
        <NavLink to={"/login"}>
          {!loginState ? (
            <>
              <Button className="linkto_register top_link_btn">注册</Button>
              <Button className="linkto_login top_link_btn">登录</Button>
            </>
          ) : (
            <Button className="linkto_login top_link_btn">注销</Button>
          )}
        </NavLink>
      </div>
    </Header>
  );
};
export default TopHeader;
