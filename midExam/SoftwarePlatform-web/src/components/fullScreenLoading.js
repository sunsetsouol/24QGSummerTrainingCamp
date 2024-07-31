import style from "./fullScreenLoading.module.css";

function FullScreenLoading() {
  return (
    <div className={style.container}>
      <div className={style["pyramid-loader"]}>
        <div className={style.wrapper}>
          <span className={`${style.side} ${style.side1}`}></span>
          <span className={`${style.side} ${style.side2}`}></span>
          <span className={`${style.side} ${style.side3}`}></span>
          <span className={`${style.side} ${style.side4}`}></span>
          <span className={style.shadow}></span>
        </div>
      </div>
    </div>
  );
}

export default FullScreenLoading;
