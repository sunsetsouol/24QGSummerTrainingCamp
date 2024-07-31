import React from "react";
import { Card, Col, Image } from "antd";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const StyledCard = styled(Card)`
  .ant-card-body {
    padding: 0;
  }
`;

const cardStyle = {
  maxWidth: 250,
  padding: "2px",
  fontSize: 12,
  lineHeight: "18px",
  borderRadius: "60px",
  border: "1px solid #E1E1E1",
};

const IndividualSoftware = ({
  imageUrl,
  description,
  tags,
  name,
  version,
  softwareId,
}) => {
  const navigate = useNavigate();
  const handleClick = (softwareId) => {
    navigate(`/header/verifybill?softwareId=${softwareId}`);
  };
  return (
    <Col span={6}>
      <StyledCard
        hoverable
        style={{
          width: 285,
          maxHeight: 300,
          padding: "16px",
          border: "1.5px solid #cdcdcd",
          cursor: "default",
        }}
      >
        <Image width={50} height={50} src={imageUrl} />
        <span style={{ marginLeft: "16px", fontSize: "16px" }}>{name}</span>
        <p
          style={{
            fontSize: "12px",
            lineHeight: "24px",
            margin: "8px 0 4px 0",
          }}
        >
          {`版本号: ${version}`}
        </p>
        <p
          style={{ fontSize: "12px", lineHeight: "24px", margin: "0 0 4px 0" }}
        >
          标签
        </p>
        <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
          {tags.map((tag, index) => (
            <Card key={index} style={cardStyle} hoverable>
              {tag}
            </Card>
          ))}
        </div>
        <p
          style={{
            margin: "8px 0 16px",
            maxHeight: "200px",
            overflow: "hidden",
            textOverflow: "ellipsis",
            display: "-webkit-box", // 兼容 Webkit 浏览器
            WebkitBoxOrient: "vertical", // 设置垂直方向的排列
            WebkitLineClamp: "3", // 限制显示的行数（根据需要调整）
          }}
          title={description}
        >
          简述:{description}
        </p>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
          }}
        >
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
              onClick={() => handleClick(softwareId)}
            >
              详情
            </button>
          </span>
        </div>
      </StyledCard>
    </Col>
  );
};

export default IndividualSoftware;
