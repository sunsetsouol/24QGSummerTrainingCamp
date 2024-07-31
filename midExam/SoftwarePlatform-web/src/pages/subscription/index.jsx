import { Row, Pagination, Drawer } from "antd";
import { useEffect, useState } from "react";
import IndividualSoftware from "./IndividualSoftware";
import { subscribeSoftwarePage } from "../../api/index";
import { useOutletContext, useNavigate } from "react-router-dom";
import MyShopping from "./myshopping";

const Subscription = () => {
  const { shoppingOrder, setShoppingOrder } = useOutletContext();
  const [open, setOpen] = useState(false);
  const [allSoftwareData, setAllSoftwareData] = useState([]);
  const [Allamout, setAllamout] = useState(0);
  const [total, setTotal] = useState(0);
  const navigate = useNavigate();

  const onChange = async (pageNumber) => {
    try {
      const response = await subscribeSoftwarePage({ page: pageNumber });
      const data = response.data.data || [];
      setAllSoftwareData(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error("Error fetching data:", error);
      setAllSoftwareData([]);
    }
  };

  const showDrawer = () => {
    setOpen(true);
  };

  const onClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    setAllamout(shoppingOrder.reduce((pre, cur) => pre + cur.price, 0));
  }, [shoppingOrder]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await subscribeSoftwarePage();
        const data = response.data || [];
        setAllSoftwareData(Array.isArray(data) ? data : []);
        setTotal(Array.isArray(data) ? data.length : 0);
      } catch (error) {
        console.error("Error fetching data:", error);
        setAllSoftwareData([]);
        setTotal(0);
      }
    };
    fetchData();
  }, []);

  const handleClick = (index) => {
    const item = allSoftwareData[index];
    if (item) {
      setShoppingOrder([...shoppingOrder, item]);
    }
  };

  const handleBuyClick = () => {
    navigate(`/header/bill`);
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        backgroundColor: "#EAF0FF",
        width: "100%",
      }}
    >
      <div
        style={{
          width: "1220px",
          height: "52px",
          lineHeight: "32px",
          fontSize: "32px",
          color: "#1E0E62",
          fontWeight: "bold",
          margin: "50px 0 35px 0",
        }}
      >
        订阅与购买
      </div>
      <Row style={{ width: 1220 }} gutter={[8, 16]}>
        {Array.isArray(allSoftwareData) && allSoftwareData.length > 0 ? (
          allSoftwareData.map((item, index) => (
            <IndividualSoftware
              index={index}
              key={index}
              {...item}
              handleClick={handleClick}
              shoppingOrder={shoppingOrder}
            />
          ))
        ) : (
            <div>没有数据可显示</div> // 提供一个用户友好的反馈
          )}
      </Row>
      <div style={{ height: "50px" }}></div>
      <Pagination
        showQuickJumper
        defaultCurrent={1}
        total={total}
        onChange={onChange}
        defaultPageSize={24}
      />
      <div
        style={{
          width: "100%",
          height: "80px",
          borderTop: "1px solid #E5DFF4",
          backgroundColor: "white",
          marginTop: "50px",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          gap: "372px",
        }}
      >
        <div>
          <span
            style={{ fontSize: "18px", marginRight: "32px", fontWeight: "400" }}
          >
            我的购物车🛒
          </span>
          <span style={{ fontSize: "16px", fontWeight: "400" }}>
            总计: {Allamout} CNY
          </span>
        </div>
        <div style={{ display: "flex", gap: "32px" }}>
          <span
            style={{
              width: "200px",
              height: "40px",
            }}
          >
            <button
              style={{
                width: "100%",
                height: "100%",
                border: "none",
                cursor: "pointer",
                borderRadius: "56px",
                color: "#633DB1",
                backgroundColor: "#D8D1FF",
              }}
              onClick={showDrawer}
            >
              查看我的购物车
            </button>
            <Drawer
              title="我的购物车"
              placement="bottom"
              closable={false}
              onClose={onClose}
              open={open}
            >
              <MyShopping
                shoppingOrder={shoppingOrder}
                setShoppingOrder={setShoppingOrder}
              />
            </Drawer>
          </span>
          <span style={{ width: "200px", height: "40px" }}>
            <button
              style={{
                width: "100%",
                height: "100%",
                border: "none",
                cursor: "pointer",
                borderRadius: "56px",
                backgroundColor: "#816AFF",
                color: "#FFFFFF",
              }}
              onClick={handleBuyClick}
              disabled={shoppingOrder.length === 0}
            >
              去支付
            </button>
          </span>
        </div>
      </div>
    </div>
  );
};

export default Subscription;
