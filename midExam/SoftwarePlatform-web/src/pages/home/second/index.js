import { NavLink } from "react-router-dom";
const flexCenterStyle = {
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
};

const HomeSecondPage = () => {
  const backgroundImage = `url(${require("../../../assets/images/微信图片_20240726225611.png")})`;
  return (
    <div
      style={{
        backgroundImage,
        width: "100%",
        height: "655px",
        backgroundSize: "cover",
        ...flexCenterStyle,
        fontFamily: "Microsoft YaHei",
        fontWeight: "bold",
      }}
    >
      <div
        style={{
          height: "116px",
          width: "557px",
          ...flexCenterStyle,
        }}
      >
        <p
          style={{
            lineHeight: "1.4",
            paragraphSpacing: "12px",
            color: "#FFFFFF",
            fontSize: "32px",
            margin: "0px",
          }}
        >
          欢迎使用工软软件管理平台
        </p>
        <br></br>
        <p
          style={{
            fontSize: "40px",
            margin: "0px",
            color: "#CFC5E6",
          }}
        >
          A Great Place to Offer Apps
        </p>
      </div>
      <div
        style={{
          height: "266px",
          width: "767px",
          marginTop: "24px",
          marginBottom: "44px",
        }}
      >
        <p
          style={{
            textAlign: "center",
            margin: "0px",
            fontSize: "28px",
            color: "#CFC5E6",
            lineHeight: "1.4",
          }}
        >
          我们的团队由一群充满激情的专业人士组成，他们拥有丰富的经验和专业知识，能够帮助您解决任何挑战。
          <br></br>
          <span style={{ display: "block", height: "16px" }}></span>
          我们鼓励创新、协作和持续学习。我们相信只有不断进步才能适应不断变化的世界。
          <br style={{ marginBottom: "16px" }}></br>
          <span style={{ display: "block", height: "16px" }}></span>
          我们的目标是提供最优质的产品与服务，以满足不同客户的需求。无论您需要什么，我们都将全力以赴地去实现。
        </p>
      </div>
      <NavLink to="/header/about" style={{ textDecoration: "none" }}>
        <div
          style={{
            height: "44px",
            width: "126px",
            borderRadius: "61px",
            backgroundColor: "#AEACFF",
          }}
        >
          <button
            style={{
              width: "100%",
              height: "100%",
              background: "transparent",
              border: "none",
              fontSize: "20px",
              cursor: "pointer",
              fontFamily: "Microsoft YaHei",
            }}
          >
            更多➡
          </button>
        </div>
      </NavLink>
    </div>
  );
};
export default HomeSecondPage;
