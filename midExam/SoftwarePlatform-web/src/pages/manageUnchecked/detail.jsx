import React, { useState } from "react";
import { Button, Modal, Image, Card } from "antd";
import "./style.css";
import { updateAuditStatus } from "../../api";
const Details = ({ software, setFlash, flash }) => {
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const softwareInfoTempId = software.softwareInfoTempId;
  const showModal = () => {
    setOpen(true);
  };

  const handleOk = async () => {
    setConfirmLoading(true);
    await updateAuditStatus({ softwareInfoTempId, status: 1 });
    setOpen(false);
    setConfirmLoading(false);
    setFlash(!flash);
  };

  const handleCancel = async () => {
    setConfirmLoading(true);
    await updateAuditStatus({ softwareInfoTempId, status: 2 });
    setOpen(false);
    setConfirmLoading(false);
    setFlash(!flash);
  };

  const tags = software.tags || ["无"];
  const typeStatus = software.typeStatus ? "更新" : "上传";
  const versionType = software.versionType ? "高级版" : "基础版";

  return (
    <>
      <Button type="primary" onClick={showModal}>
        审核
      </Button>
      <Modal
        title={`来自${software.author}的${typeStatus}`}
        open={open}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={1200}
        okText="通过"
        cancelText="拒绝"
        maskClosable={false} // Disable closing Modal by clicking the mask
      >
        <div className="card-content">
          <Image width={50} height={50} src={software.softwareImage} />
          <span className="card-title">
            {software.softwareName} ({versionType})
          </span>
          <button className="status-button">
            {software.passedStringStatus}
          </button>
        </div>

        <p style={{ fontSize: 14, fontWeight: "bold", margin: "20px 0 20px" }}>
          版本号: {software.version}
        </p>
        <p>标签</p>
        <div className="tags-container">
          {tags.map((tag, index) => (
            <Card key={index} className="tag-card" hoverable>
              {tag}
            </Card>
          ))}
        </div>

        <p className="description">简述: {software.briefDescription}</p>
        <p className="description">详述: {software.detailedDescription}</p>
        <p className="description">价格: {software.price}:00 CNY</p>

        <p>
          <a
            href={software.winUrl}
            style={{ cursor: "pointer" }}
            onClick={(e) => e.stopPropagation()}
          >
            winUrl: {software.winUrl}
          </a>
        </p>
        <p>
          <a
            href={software.macUrl}
            style={{ cursor: "pointer" }}
            onClick={(e) => e.stopPropagation()}
          >
            macUrl: {software.macUrl}
          </a>
        </p>
        <p>
          <a
            href={software.linuxUrl}
            style={{ cursor: "pointer" }}
            onClick={(e) => e.stopPropagation()}
          >
            linuxUrl: {software.linuxUrl}
          </a>
        </p>
        <Button
          type="primary"
          style={{ marginTop: 20 }}
          onClick={() => setOpen(false)}
        >
          代办
        </Button>
      </Modal>
    </>
  );
};

export default Details;
