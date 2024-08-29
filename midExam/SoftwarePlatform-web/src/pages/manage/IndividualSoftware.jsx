import React from "react";
import { Card, Col, Image, Drawer } from "antd";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import Upload from "./upload";
const StyledCard = styled(Card)`
  width: 285px;
  max-height: 300px;
  padding: 16px;
  border: 1.5px solid #cdcdcd;
  cursor: default;

  .ant-card-body {
    padding: 0;
  }

  .card-content {
    display: flex;
    align-items: center;
  }

  .card-title {
    margin-left: 16px;
    font-size: 16px;
    margin-right: 16px;
  }

  .status-button,
  .details-button,
  .change-button {
    width: 60px;
    height: 30px;
    border-radius: 100px;
    background-color: #e5dff4;
    border: none;
    cursor: pointer;
    font-size: 14px;
    line-height: 1.6;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #633db1;
  }

  .details-button {
    background-color: #e5dff4;
  }

  .change-button {
    background-color: #816aff;
    color: #ffffff;
  }

  .description {
    font-size: 12px;
    line-height: 24px;
    margin: 8px 0 16px;
    max-height: 200px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
  }

  .tags-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
  }

  .tag-card {
    max-width: 250px;
    padding: 2px;
    font-size: 12px;
    line-height: 18px;
    border-radius: 60px;
    border: 1px solid #e1e1e1;
    background-color: #e5dff4;
    color: #633db1;
  }
`;

const IndividualSoftware = ({
  imageUrl,
  description,
  tags,
  name,
  version,
  softwareId,
  software,
}) => {
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);
  const showDrawer = () => {
    setOpen(true);
  };
  const onClose = () => {
    setOpen(false);
  };
  const handleClick = (softwareId) => {
    navigate(`/header/verifybill?softwareId=${softwareId}`);
  };

  return (
    <Col span={6}>
      <StyledCard hoverable>
        <div className="card-content">
          <Image width={50} height={50} src={imageUrl} />
          <span className="card-title">{name}</span>
          <button className="status-button">
            {software.status ? "已上架" : "已下架"}
          </button>
        </div>

        <p>版本号: {version}</p>
        <p>标签</p>
        <div className="tags-container">
          {tags.map((tag, index) => (
            <Card key={index} className="tag-card" hoverable>
              {tag}
            </Card>
          ))}
        </div>

        <p className="description" title={description}>
          简述: {description}
        </p>

        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <button
            className="details-button"
            onClick={() => handleClick(softwareId)}
          >
            详情
          </button>
          <button className="change-button" onClick={showDrawer}>
            更改
          </button>
          <Drawer
            title="产品信息修改"
            onClose={onClose}
            open={open}
            width={720}
          >
            <Upload software={software} />
          </Drawer>
        </div>
      </StyledCard>
    </Col>
  );
};

export default IndividualSoftware;
