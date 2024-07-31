import { Button, Modal, Form, Input, Radio, Alert } from "antd";
import { softwareUpload } from "../../api";
import React, { useState, useRef } from "react";
import styles from "./person.module.css";
const { TextArea } = Input;
const ModalAlert = ({ status }) => {
  const [isModalOpen, setIsModalOpen] = useState(status);
  const [userId, setUserId] = useState(localStorage.getItem("userIdSf"));
  const [image, setImage] = useState("");

  // 预览图片盒子状态
  const [imageBox, setImageBox] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);
  const [alert, setAlert] = useState(false);
  const imageRef = useRef(null);
  const softwareNameRef = useRef(null);
  const [form] = Form.useForm();
  const showModal = () => {
    form.resetFields();
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
    setImageBox(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
    setImageBox(false);
  };
  // 节流函数
  function throttle(func, limit) {
    let lastFunc;
    let lastRan;

    return function (...args) {
      if (!lastRan) {
        func.apply(this, args);
        lastRan = Date.now();
      } else {
        clearTimeout(lastFunc);
        lastFunc = setTimeout(() => {
          if (Date.now() - lastRan >= limit) {
            func.apply(this, args);
            lastRan = Date.now();
          }
        }, limit - (Date.now() - lastRan));
      }
    };
  }
  //   上传成功弹出框
  const showAlert = () => {
    setAlert(true);
    setTimeout(() => {
      setAlert(false);
    }, 3000);
  };
  // console.log(userId);
  // 上传软件
  const upload = async (newFormData) => {
    try {
      const response = await softwareUpload(newFormData);
      showAlert();
      softwareNameRef.current.input.value = "";
      form.resetFields(); // 清空表单
      setImageBox(false);
    } catch (error) {
      console.error("Error fetching models:", error);
    }
  };
  // 获取表单信息
  const onFinish = (values) => {
    // console.log('Success:', values);
    // 创建 FormData 对象
    const formData = new FormData();
    formData.append("userId", userId);
    // 遍历表单数据并添加到 FormData 中
    for (const key in values) {
      // 确保只添加非空值
      if (
        values[key] !== undefined &&
        values[key] !== null &&
        values[key] !== "" &&
        key !== "tags"
      ) {
        formData.append(key, values[key]);
      }
    }
    const tagsString = values.tags; // 从表单获取原始字符串
    if (tagsString) {
      const tagsArray = tagsString.split("，").map((tag) => tag.trim()); // 按照逗号分隔并去掉空格
      formData.append("tags", tagsArray); // 将数组转成 JSON 字符串后存入 FormData
    }
    // 如果需要处理文件上传，需要额外的逻辑来获取文件输入
    ["winPackage", "linuxPackage", "macPackage", "softwareImage"].forEach(
      (fileInputName) => {
        const fileInput = document.querySelector(
          `input[name="${fileInputName}"]`
        );
        if (fileInput && fileInput.files[0]) {
          formData.append(fileInputName, fileInput.files[0]);
          console.log(fileInput.files[0]);
        }
      }
    );
    upload(formData);
  };
  // 图片预览
  const imageInput = (e) => {
    setImageBox(true);
    const file = e.target.files[0];
    // console.log(e.target.files[0]);
    setSelectedFile(file);
    if (file) {
      // setImage(file);

      const reader = new FileReader();
      // setChangeImage(true)
      reader.onloadend = () => {
        setImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };
  // 取消图片预览
  const cancelPreview = () => {
    setImageBox(false);
    setSelectedFile(null);
  };
  const onFinishFailed = (errorInfo) => {
    // console.log('Failed:', errorInfo);
  };
  return (
    <>
      <Button type="primary" onClick={showModal} className={styles.openbox}>
        上传软件
      </Button>
      <Modal
        title="上传我的软件"
        footer={null}
        width={600}
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        {alert && (
          <Alert
            message="Success Tips"
            description="上传成功"
            type="success"
            showIcon
          />
        )}
        <Form
          name="basic"
          form={form}
          labelCol={{
            span: 8,
          }}
          wrapperCol={{
            span: 16,
          }}
          style={{
            maxWidth: 600,
            marginTop: "20px",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
          initialValues={{
            remember: true,
          }}
          onFinish={throttle(onFinish, 500)}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            label="softwareName："
            name="softwareName"
            rules={[
              {
                required: true,
                message: "Please input your softwareName!",
              },
            ]}
          >
            <Input
              className={styles.shangchuan}
              ref={softwareNameRef}
              placeholder="软件名字"
            />
          </Form.Item>

          <Form.Item
            label="version"
            name="version"
            rules={[
              {
                required: true,
                message: "Please input your version!",
              },
            ]}
          >
            <Input className={styles.shangchuan} placeholder="版本号" />
          </Form.Item>

          <Form.Item
            label="versionType"
            name="versionType"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Radio.Group>
              <Radio value={0}> 普通版 </Radio>
              <Radio value={1}> 高级版 </Radio>
            </Radio.Group>
          </Form.Item>

          <Form.Item
            label="description"
            name="description"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <div
              style={{
                width: "200px",
                display: "flex",
                justifyContent: "center",
              }}
            >
              <TextArea rows={4} cols={300} placeholder="描述：" />
            </div>
          </Form.Item>
          <Form.Item
            label="winPackage:"
            name="winPackage"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Input type="file" className={styles.shangchuan}></Input>
          </Form.Item>
          <Form.Item
            label="linuxPackage:"
            name="linuxPackage"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Input type="file" className={styles.shangchuan}></Input>
          </Form.Item>
          <Form.Item
            label="macPackage:"
            name="macPackage"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Input type="file" className={styles.shangchuan}></Input>
          </Form.Item>
          <Form.Item
            label="tags:"
            name="tags"
            rules={[
              {
                required: true,
                message: "Please input your tags!",
              },
            ]}
          >
            <Input
              className={styles.shangchuan}
              placeholder="标签（用逗号隔开）"
            ></Input>
          </Form.Item>
          {imageBox && (
            <div className={styles.preview}>
              <Button className={styles.cancel} onClick={cancelPreview}>
                x
              </Button>
              <img src={image} alt="preview"></img>
            </div>
          )}
          <Form.Item
            label="softwareImage:"
            name="softwareImage"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Input
              type="file"
              accept="image/*"
              className={styles.shangchuan}
              value={selectedFile ? selectedFile.name : ""}
              onChange={imageInput}
              ref={imageRef}
            ></Input>
          </Form.Item>
          <Form.Item
            label="price:"
            name="price"
            // rules={[
            //     {
            //         required: true,
            //         message: 'Please input your softwareName!',
            //     },
            // ]}
          >
            <Input className={styles.shangchuan} placeholder="价格"></Input>
          </Form.Item>
          <Form.Item
            label="typeStatus"
            name="typeStatus"
            rules={[
              {
                required: true,
                message: "Please input your typeStatus!",
              },
            ]}
          >
            <Radio.Group>
              <Radio value={0}> 新软件 </Radio>
              <Radio value={1}> 新版本 </Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              Submit
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
export default ModalAlert;
