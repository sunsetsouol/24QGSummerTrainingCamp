import "./layout.css";
import { Layout } from "antd";
import { Outlet } from "react-router-dom";
import TopHeader from "./topheader";
import BottomFooter from "./bottomfooter";
import { useState } from "react";

const TopMenu = () => {
  const [shoppingOrder, setShoppingOrder] = useState([]);

  return (
    <Layout>
      <TopHeader></TopHeader>
      <div className="main_content">
        <Outlet context={{ shoppingOrder, setShoppingOrder }}></Outlet>
      </div>
      <BottomFooter></BottomFooter>
    </Layout>
  );
};
export default TopMenu;
