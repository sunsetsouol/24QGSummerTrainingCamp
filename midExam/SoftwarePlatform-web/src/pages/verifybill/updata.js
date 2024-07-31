import styles from "./verifybill.module.css"
import { Card,List,Button } from "antd"
const data = [
    {
        title: '版本号：2.1.0.1',
        time:'时间：2024-05-25 11:19'
    },
    {
        title: '版本号：2.1.0.1',
        time: '时间：2024-01-07 13:19'
    },
    {
        title: '版本号：2.1.0.1',
        time: '时间：2024-06-05 01:33'
    },
    {
        title: '版本号：2.1.0.1',
        time: '时间：2024-07-25 11:01'
    },
];
const Update = () => {
    return (
        <div className={styles.update}>
            <h3 className={styles.title}>更新</h3>
            <div className={styles.update_content}>
                <Card style={{ width: '100%' }}
                    hoverable
                    className={styles.updatecard}>
                        <div className={styles.updatelist}>
                        <List
                            itemLayout="horizontal"
                            dataSource={data}
                            renderItem={(item, index) => (
                                <List.Item extra={<a className={styles.more} href='#'>更多</a>}>
                                    <List.Item.Meta
                                        title={<div><span className={styles.update_version}>{item.title}</span><span className={styles.update_time}>{item.time}</span></div>}
                                        description={<h3 className={styles.description}>更新内容1.19&1.20双版本更新，全新冒险体验！大型多人玩法方块乐园来袭，无限可能等你探索！封印解除，幽匿崛起！化身暗域守望，体...</h3>}
                                    />
                                </List.Item>
                            )}
                        />
                        </div>
                    <div className={styles.check_btn_box}><Button className={styles.check_btn}>查看更多历史</Button></div>
                </Card>
            </div>
        </div>
    )
}
export default Update;