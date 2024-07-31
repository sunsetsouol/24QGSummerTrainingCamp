import styles from "./layoutfooter.module.css"
import { Layout } from 'antd';
import { DownOutlined } from "@ant-design/icons"
const { Footer } = Layout;
const BottomFooter=()=>{
    return(
        <Footer className={styles.bottom_footer}>
            <div className={styles.bottom_footer_content}>
                <div className={styles.footer_top}>
                    <div className={styles.footer_left}>
                        <h3>工业软件管理平台</h3>
                        <h6>请务必查看我们的《使用条款》和《隐私政策》</h6>
                    </div>
                    <div className={styles.footer_middle}>
                        <div className={styles.footer_product}>
                            <h6 className={styles.title}>产品</h6>
                            <h6>最新上线</h6>
                            <h6>价格</h6>
                            <h6>使用须知</h6>
                        </div>
                        <div className={styles.footer_about}>
                            <h6 className={styles.title}>关于我们</h6>
                            <h6>团队介绍</h6>
                            <h6>团队账号</h6>
                            <h6>加入我们</h6>
                        </div>
                        <div className={styles.footer_callus}>
                            <h6 className={styles.title}>联系我们</h6>
                            <h6>电话: 020-812-255</h6>
                            <h6>邮箱:fildineesoe@gmail.com</h6>
                            <h6>地址: 0123 Some place</h6>
                        </div>
                    </div>
                    <div className={styles.footer_right}>
                        <h6 className={styles.language}>简体中文   {<DownOutlined />}</h6>
                    </div>
                </div>
                <div className={styles.footer_bottom}>
                    <div className={styles.belong}>
                        <h6>© 所有权利归QG STUDIO所属</h6>
                    </div>
                </div>
            </div>
        </Footer>
    )
}
export default BottomFooter;