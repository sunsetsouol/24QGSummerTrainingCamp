import FourthCard from "../fourthCard/fourthcard";
import styles from "./fourth.module.css"
import { Avatar, List } from 'antd';

const HomeFourthPage = () => {

    const data = [
        {
            title: 'A good software that rotates my code.',
            description: '一个不错的软件，让我的代码旋转.',
        },
        {

            title: 'Im extremely satisfied with this software, even to the point of giving it a 9 out of 10.',
            description: '我对这个软件有十分甚至九分的满意。',

        },
        {
            title: '私の牢大の直升机は、このウェブサイトを見つけてから陨落しました。',
            description: '我老大的直升机就是发现了这个网站才坠机的。',

        },
        {
            title: 'Ctrl+C and Ctrl+V are essential tools for engineers.',
            description: 'Ctrl+C＆Ctrl+Vは、エンジニアの必須ツールです.',

        },
    ];


    return (
        <div className={styles.home_fourthpage}>
            <h1 className={styles.title}>优秀的使用反馈</h1>
            <div className={styles.content}>
                <List
                    className={styles.List}
                    itemLayout="horizontal"
                    dataSource={data}
                    renderItem={(item, index) => (
                        <List.Item className={styles.MetaList}>
                            <List.Item.Meta
                                avatar={<Avatar src={`https://api.dicebear.com/7.x/miniavs/svg?seed=${index}`} />}
                                title={<a href="https://ant.design">{item.title}</a>}
                                description={item.description}
                                className={styles.MetaList}
                            />
                        </List.Item>
                    )}
                />
            </div>
           
        </div>
    )
}
export default HomeFourthPage;