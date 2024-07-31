import HomeFifthPage from "./fifth/fifith";
import HomeFirstPage from "./first/first";
import HomeFourthPage from "./fourth/fourth";
import HomeThirdPage from "./third/third";
import HomeSecondPage from "./second";


const Home = () => {




  return (
    <div className="home">
      <HomeFirstPage></HomeFirstPage>
      <HomeSecondPage></HomeSecondPage>
      <HomeThirdPage></HomeThirdPage>
      <HomeFourthPage></HomeFourthPage>
      <HomeFifthPage></HomeFifthPage>
    </div>
  );
};
export default Home;
