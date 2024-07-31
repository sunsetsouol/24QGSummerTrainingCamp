import React, { useEffect, useState } from "react";
import { Row, Col, Pagination } from "antd";
import IndividualSoftware from "./IndividualSoftware";
import { getAuditRecord } from "../../api";

function ManageUnchecked() {
  const [allSoftwareData, setAllSoftwareData] = useState([]);
  const [total, setTotal] = useState(0);
  const [flash, setFlash] = useState(false);
  useEffect(() => {
    const fetchData = async () => {
      const response = await getAuditRecord({});

      setTotal(response.data.length);
      setAllSoftwareData(response.data);
    };
    fetchData();
  }, [flash]);
  const onChange = async (pageNumber) => {
    console.log("Page: ", pageNumber);
    const response = await getAuditRecord({
      page: pageNumber,
    });
    setTotal(response.data.length);
    setAllSoftwareData(response.data);
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
        产品库
      </div>
      <Row style={{ width: 1200 }} gutter={[8, 16]}>
        {allSoftwareData ? (
          allSoftwareData.map((software, index) => (
            <Col key={software.softwareInfoTempId} span={6}>
              <IndividualSoftware
                {...software}
                software={software}
                setFlash={setFlash}
                flash={flash}
              />
            </Col>
          ))
        ) : (
          <></>
        )}
      </Row>
      {console.log(999, allSoftwareData)}
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

export default ManageUnchecked;
