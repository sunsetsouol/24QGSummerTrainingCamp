import React from 'react';
import { Select, Card } from 'antd';
import styles from './shopsoftware.module.css'
const Selectcard = () => {
    return (
        <div className={styles.selectcardbox}>
            <Card style={{ width: '100%' }} className={styles.selectcard}>
                <div className={styles.inputbox}>
                    <div className={styles.everyselect}>
                        <span>选项1</span>
                        <Select
                            defaultValue="lucy"
                            className={styles.select}
                            style={{
                                width: 120,
                            }}
                            
                            options={[
                                {
                                    value: 'lucy',
                                    label: 'Lucy',
                                },
                            ]}
                        />
                    </div>
                    <div className={styles.everyselect}>
                        <span>选项2</span>
                        <Select
                            defaultValue="lucy"
                            className={styles.select}
                            style={{
                                width: 120,
                            }}
                            
                            options={[
                                {
                                    value: 'lucy',
                                    label: 'Lucy',
                                },
                            ]}
                        />
                    </div>
                    <div className={styles.everyselect}>
                        <span>选项3</span>
                        <Select
                            defaultValue="lucy"
                            className={styles.select}
                            style={{
                                width: 120,
                            }}
                            
                            options={[
                                {
                                    value: 'lucy',
                                    label: 'Lucy',
                                },
                            ]}
                        />
                    </div>
                    <div className={styles.everyselect}>
                        <span>选项4</span>
                        <Select
                            defaultValue="lucy"
                            className={styles.select}
                            style={{
                                width: 120,
                            }}
                            
                            options={[
                                {
                                    value: 'lucy',
                                    label: 'Lucy',
                                },
                            ]}
                        />
                    </div>
                    
                </div>
            </Card>
        </div>
    );
}

export default Selectcard;
