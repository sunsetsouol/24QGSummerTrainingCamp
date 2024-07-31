import styles from "./person.module.css"
import { Card, Button, List, Modal } from "antd"
import { checkLatestSoftware, downloadSoftware, getCommit } from "../../api"
import { useState, useEffect } from 'react'
const data = [

];
const MyMessage = () => {
    const [data, setData] = useState([])
    const [isDownloadModalOpen, setIsDownloadModalOpen] = useState(false);
    const [clickSoftWare, setClickSoftWare] = useState({})


    useEffect(() => {
        async function receiveInformation() {
            try {
                const response = await checkLatestSoftware(localStorage.getItem('userIdSf'))
                if (response.data)
                    setData(response.data)
            } catch (error) {
                console.error('Error sending verification code:', error);
            }
        }
        receiveInformation()
    }, [])



    const toggleDownloadModal = (target, clickData) => {
        if (clickData) {
            setClickSoftWare(clickData)
        }
        setIsDownloadModalOpen(target);
    };


    //点击下载后
    const handleDownload = async (target) => {
        try {
            const response = await downloadSoftware(
                clickSoftWare.softwareId,
                localStorage.getItem('userIdSf'),
                clickSoftWare.versionType,
                clickSoftWare.version
            );
            const responseUrl = await getCommit(
                clickSoftWare.softwareId,
                localStorage.getItem('userIdSf'),
                clickSoftWare.versionType,
                clickSoftWare.version
            )

            let url = responseUrl.data
            if (target === 'windows') {
                url = url.winUrl;
            }
            if (target === 'Linux') {
                url = url.linuxUrl;
            }
            if (target === 'mac') {
                url = url.macUrl;
            }
            const link = document.createElement("a");
            link.href = url;
            link.download = ""; // 设置 download 属性，可以指定下载时的文件名
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            toggleDownloadModal(false);
            const responseUpData = await checkLatestSoftware(localStorage.getItem('userIdSf'))
            // console.log(responseUpData)
            if (responseUpData.data)
                setData(responseUpData.data)
        } catch (error) {
            console.error("Error fetching models:", error);
        }
    };



    return (
        <div className={styles.mymessage_part}>
            <h3 className={styles.title}>更新</h3>
            <div className={styles.myprivate_box}>
                <Card hoverable style={{ width: '100%' }}
                    className={styles.privatecard}>
                    <div className={styles.private_content}>
                        <List
                            dataSource={data}
                            renderItem={(item) => (
                                <List.Item>
                                    <div className={styles.everymessage}>
                                        <div className={styles.detail}> <span className={styles.cue}></span>{item.softwareName}</div>
                                        <div className={styles.detail_right}>
                                            <div className={styles.messagetime}>版本号：{item.version}</div>
                                            <div className={styles.disknow}>
                                                {
                                                    item.versionType ? <Button className={styles.highVersion} value={data} onClick={() => toggleDownloadModal(true, item)}>高级版</Button> : <Button onClick={() => toggleDownloadModal(true, item)} className={styles.normalVersion} >普通版</Button>
                                                }
                                            </div>
                                            {/* 已读 颜色变换类名 isknow和disknow */}
                                        </div>
                                    </div>

                                </List.Item>
                            )}
                        />
                    </div>
                </Card>
            </div>
            <Modal
                title="请选择下载方式"
                open={isDownloadModalOpen}
                onOk={() => toggleDownloadModal(false)}
                onCancel={() => toggleDownloadModal(false)}
                footer=""
            >
                <div className={styles.choose_system}>
                    <Button
                        type="primary"
                        className={styles.system_btn}
                        onClick={() => handleDownload('windows')}
                    >
                        Windows下载
              </Button>
                    <Button
                        type="primary"
                        className={styles.system_btn}
                        onClick={() => handleDownload('mac')}
                    >
                        mac下载
              </Button>
                    <Button
                        type="primary"
                        className={styles.system_btn}
                        onClick={() => handleDownload('Linux')}
                    >
                        Linux下载
              </Button>
                </div>
            </Modal>
        </div>
    )
}
export default MyMessage