import React, { useEffect } from "react";
import { Card, Col, Image } from "antd";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

const StyledCard = styled(Card)`
  .ant-card-body {
    padding: 0;
  }
`;

const IndividualSoftware = ({
  index,
  softwareId,
  softwareImage,
  softwareName,
  versionType,
  description,
  price,
  handleClick,
  shoppingOrder,
}) => {
  const [isAddToShoppingOrder, setIsAddToShoppingOrder] = useState(false);
  shoppingOrder = shoppingOrder ? shoppingOrder : [];
  useEffect(() => {
    // 使用 some 方法检查是否有匹配的项
    const isInShoppingOrder = shoppingOrder.some(
      (item) =>
        item.softwareId === softwareId && item.versionType === versionType
    );

    // 更新状态
    setIsAddToShoppingOrder(isInShoppingOrder);
  }, [shoppingOrder, softwareId, versionType]);
  const handleAddToCartClick = (e) => {
    e.stopPropagation(); //阻止冒泡
    handleClick(index);
    setIsAddToShoppingOrder(true);
  };
  const navigate = useNavigate();
  const handleFindClick = (softwareId) => {
    navigate(`/header/verifybill?softwareId=${softwareId}`);
  };
  return (
    <Col span={6}>
      <StyledCard
        hoverable
        style={{
          width: 285,
          padding: "16px",
          border: "1.5px solid #cdcdcd",
          cursor: "default",
        }}
      >
        <Image
          width={50}
          height={50}
          src={softwareImage}
          onClick={(e) => e.stopPropagation()}
        />
        <span style={{ marginLeft: "16px", fontSize: "16px" }}>
          {softwareName}
          <span style={{ fontWeight: "bold", marginLeft: "8px" }}>
            {versionType ? "(高级版)" : "(基础版)"}
          </span>
        </span>
        <p
          style={{
            margin: "8px 0 16px",
            maxHeight: "200px",
            overflow: "hidden",
            textOverflow: "ellipsis",
            display: "-webkit-box", // 兼容 Webkit 浏览器
            WebkitBoxOrient: "vertical", // 设置垂直方向的排列
            WebkitLineClamp: "8", // 限制显示的行数（根据需要调整）
          }}
          title={description}
        >
          简述:{description}
        </p>
        <div
          style={{
            marginBottom: "16px",
            fontSize: "16px",
            fontWeight: "bold ",
          }}
        >
          {price}.00 CNY/年
        </div>
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <span
            style={{
              width: "60px",
              height: "30px",
              borderRadius: "100px",
              backgroundColor: "#E5DFF4",
            }}
          >
            <button
              style={{
                width: "100%",
                height: "100%",
                border: "none",
                backgroundColor: "transparent",
                cursor: "pointer",
                color: "#633DB1",
                fontSize: "14px",
                lineHeight: "1.6",
              }}
              onClick={() => handleFindClick(softwareId)}
            >
              详情
            </button>
          </span>
          <span
            style={{
              width: "116px",
              height: "30px",
              borderRadius: "100px",
              backgroundColor: "#E5DFF4",
            }}
          >
            <button
              style={{
                width: "100%",
                height: "100%",
                border: "none",
                backgroundColor: "transparent",
                cursor: "pointer",
                color: isAddToShoppingOrder ? "#CCCCCC" : "#633DB1",
                fontSize: "14px",
                lineHeight: "1.6",
              }}
              disabled={isAddToShoppingOrder}
              onClick={handleAddToCartClick}
            >
              添加至购物车
            </button>
          </span>
        </div>
      </StyledCard>
    </Col>
  );
};

export default IndividualSoftware;
