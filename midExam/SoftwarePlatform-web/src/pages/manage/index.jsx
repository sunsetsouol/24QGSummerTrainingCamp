import React, { useEffect, useState } from "react";
import { Row, Col, Pagination } from "antd";
import IndividualSoftware from "./IndividualSoftware";

import { myProductPage } from "../../api";

function Manage() {
  const [allSoftwareData, setAllSoftwareData] = useState([]);
  const [total, setTotal] = useState(0);
  useEffect(() => {
    const fetchData = async () => {
      const response = await myProductPage({});
      setTotal(response.data.total);
      setAllSoftwareData(response.data.data);
    };
    fetchData();
  }, []);
  const onChange = async (pageNumber) => {
    const response = await myProductPage({
      page: pageNumber,
    });
    setTotal(response.data.total);
    setAllSoftwareData(response.data.data);
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
        上架库
      </div>
      <Row style={{ width: 1200 }} gutter={[8, 16]}>
        {allSoftwareData.map((software, index) => (
          <Col key={software.softwareId} span={6}>
            <IndividualSoftware
              name={software.softwareName}
              version={software.version}
              tags={software.tags}
              description={software.description}
              imageUrl={software.softwareImage}
              softwareId={software.softwareId}
              software={software}
            />
          </Col>
        ))}
      </Row>
      <Pagination
        showQuickJumper
        defaultCurrent={1}
        total={total}
        onChange={onChange}
        defaultPageSize={12}
        style={{ margin: "50px 0 50px 0" }}
      />
    </div>
  );
}

export default Manage;
