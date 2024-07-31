import React from "react";
import { Button, Form, Input, Select, message } from "antd";
import SelectTags from "./select";
import { updateSoftwareInfo } from "../../api";
const { TextArea } = Input;

const Upload = ({ software }) => {
  const [form] = Form.useForm();

  const onChange = (value) => {
    console.log(`selected ${value}`);
  };

  const onFinish = async (values) => {
    try {
      // Convert downloadCount to a number
      const transformedValues = {
        ...values,
        downloadCount: Number(values.downloadCount),
      };

      console.log("Transformed Form values:", transformedValues);

      // // Send POST request to the server
      await updateSoftwareInfo(transformedValues);
      message.success("修改成功");
      // if (response.status === 200) {
      //   message.success("Form submitted successfully!");
      // } else {
      //   message.error("Submission failed!");
      // }
    } catch (error) {
      console.error("Error submitting form:", error);
      message.error("An error occurred while submitting the form.");
    }
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <Form
      form={form}
      name="basic"
      labelCol={{ span: 8 }}
      wrapperCol={{ span: 16 }}
      style={{ maxWidth: 1000 }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete="off"
      initialValues={{ ...software }}
    >
      <Form.Item
        label="softwareId"
        name="softwareId"
        rules={[{ required: true, message: "Please input your softwareId!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="userId"
        name="userId"
        rules={[{ required: true, message: "Please input your userId!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="softwareName"
        name="softwareName"
        rules={[{ required: true, message: "Please input your softwareName!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="author"
        name="author"
        rules={[{ required: true, message: "Please input your author!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="description"
        name="description"
        rules={[{ required: true, message: "Please input your description!" }]}
      >
        <TextArea rows={4} />
      </Form.Item>

      <Form.Item
        label="downloadCount"
        name="downloadCount"
        rules={[
          { required: true, message: "Please input your downloadCount!" },
        ]}
      >
        <Input type="number" />
      </Form.Item>

      <Form.Item
        label="status"
        name="status"
        rules={[{ required: true, message: "Please select your status!" }]}
      >
        <Select
          showSearch
          placeholder="Select a person"
          optionFilterProp="label"
          onChange={onChange}
          options={[
            { value: "0", label: "0" },
            { value: "1", label: "1" },
          ]}
        />
      </Form.Item>

      <Form.Item
        label="tags"
        name="tags"
        rules={[{ required: true, message: "Please input your tags!" }]}
      >
        <SelectTags
          value={form.getFieldValue("tags")}
          onChange={(tags) => form.setFieldsValue({ tags })}
        />
      </Form.Item>

      <Form.Item
        label="createTime"
        name="createTime"
        rules={[{ required: true, message: "Please input your createTime!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default Upload;
