import { CloseOutlined } from "@ant-design/icons";
import { Image } from "antd";
function MyShopping({ shoppingOrder, setShoppingOrder }) {
  const handleClick = (softwareId, versionType, e) => {
    e.stopPropagation();
    const updatedOrder = shoppingOrder.filter(
      (item) =>
        !(item.softwareId === softwareId && item.versionType === versionType)
    );

    setShoppingOrder(updatedOrder);
  };
  return (
    <>
      <div
        style={{
          margin: "auto",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          width: "1200px",
          fontWeight: "bold",
          fontSize: "20px",
          borderBottom: "1px solid #CCCCCC",
        }}
      >
        <span style={{ width: "450px" }}>产品信息</span>
        <span style={{ width: "250px" }}>类型</span>
        <span style={{ width: "200px" }}>数量</span>
        <span style={{ width: "200px" }}>价格</span>
        <span style={{ width: "50px" }}></span>
      </div>
      {shoppingOrder.map((item, index) => (
        <div
          key={index}
          style={{
            margin: "auto",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            width: "1200px",
            fontSize: "20px",
            borderBottom: "1px solid #CCCCCC",
          }}
        >
          <span style={{ width: "450px", margin: "18px 0 18px" }}>
            <Image width={50} height={50} src={item.softwareImage} />
            <span> {item.softwareName}</span>
          </span>
          <span style={{ width: "250px" }}>
            {item.versionType ? "(高级版)" : "(基础版)"}
          </span>
          <span style={{ width: "200px" }}>1</span>
          <span style={{ width: "200px" }}>{item.price} CNY</span>
          <span style={{ width: "50px" }}>
            <CloseOutlined
              style={{ cursor: "pointer" }}
              onClick={(e) => handleClick(item.softwareId, item.versionType, e)}
            />
          </span>
        </div>
      ))}
    </>
  );
}

export default MyShopping;
