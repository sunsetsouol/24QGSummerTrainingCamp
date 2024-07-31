import React, { useRef, useState, useEffect } from "react";
import { PlusOutlined, CloseOutlined } from "@ant-design/icons";
import { Button, Divider, Input, Select, Space } from "antd";

const SelectTags = ({ value = [], onChange }) => {
  const [items, setItems] = useState(value);
  const [name, setName] = useState("");
  const inputRef = useRef(null);

  useEffect(() => {
    setItems(value);
  }, [value]);

  const onNameChange = (event) => {
    setName(event.target.value);
  };

  const addItem = (e) => {
    e.preventDefault();
    if (name) {
      const newItems = [...items, name];
      setItems(newItems);
      onChange(newItems);
      setName("");
    }
    setTimeout(() => {
      inputRef.current?.focus();
    }, 0);
  };

  const removeItem = (item) => {
    const newItems = items.filter((i) => i !== item);
    setItems(newItems);
    onChange(newItems);
  };

  return (
    <Select
      mode="tags"
      style={{ width: "100%" }}
      placeholder="Select tags"
      dropdownRender={(menu) => (
        <>
          {menu}
          <Divider style={{ margin: "8px 0" }} />
          <Space style={{ padding: "0 8px 4px" }} direction="vertical">
            <Input
              placeholder="Please enter tag"
              ref={inputRef}
              value={name}
              onChange={onNameChange}
              onKeyDown={(e) => e.stopPropagation()}
            />
            <Button type="text" icon={<PlusOutlined />} onClick={addItem}>
              Add tag
            </Button>
          </Space>
          <Space direction="vertical" style={{ padding: "0 8px" }}>
            {items.map((item) => (
              <Space
                key={item}
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                  width: "100%",
                }}
              >
                <span>{item}</span>
                <Button
                  type="text"
                  icon={<CloseOutlined />}
                  onClick={() => removeItem(item)}
                  style={{ marginLeft: "8px" }}
                />
              </Space>
            ))}
          </Space>
        </>
      )}
      options={items.map((item) => ({
        label: item,
        value: item,
      }))}
    />
  );
};

export default SelectTags;
