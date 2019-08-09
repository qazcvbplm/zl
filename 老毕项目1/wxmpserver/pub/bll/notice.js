const com = require('../utils/common')
const model = require('../model/wxmp')
const config = require('./../config/config')
const wxtoken = require('./wxtoken');
let wechatAPI = require('wechat-api');
let api = new wechatAPI(config.APP_ID, config.APP_SECRET);
const app = {
    async sendNotice(ctx) {

        let form = ctx.request.query;
        let data = await model.getNoticePersons({
            a_id: form.a_id
        })
        let ordata = (await model.getOrder(form.oid))[0]
        let tkn = await wxtoken.getToken()
        let token = ''
        if (tkn.code == 0) {
            token = tkn.token
            for (let i in data) {
                let send = this.noticeMsg(ordata);
                console.log(send)
                api.sendTemplate(data[i].openid, 's9ahgp1MbfqPLiNrajNaP8JKO-be45WAqC4rqEaCGuo', 'http://www.hbhzdtn.com', send, function (err, result) {
                    if (err) {
                        console.log(err);
                    } else {
                        console.log(result);
                    }
                });
            }
        } else {
            console.log(tkn)
        }


    },
    noticeMsg(form) {
        // "miniprogram": {
        //     "appid": config.MINI_APP_ID,
        //     "pagepath": "/pages/order/detail/detail?id=" + form.id
        // },
        let msg = {
            "first": {
                "value": "有新订单喽！",
                "color": "#173177"
            },
            "keyword1": {
                "value": (form.order_num || '0000'),
                "color": "#173177"
            },

            "keyword2": {
                "value": form.pay_time || new Data(),
                "color": "#173177"
            },
            "keyword3": {
                "value": form.title || '其他帮助',
                "color": "#173177"
            },
            "keyword4": {
                "value": form.mu || '',
                "color": "#173177"
            },
            "remark": {
                "value": "请及时接单",
                "color": "#173177"
            }

        }
        // msg.data = JSON.stringify(msg.data)
        return msg
    },
    async setMeun() {
        let menu = {
            "button": [{
                "name": "求帮助",
                "type": 'miniprogram',
                "url": 'http://www.hbhzdtn.com',
                "appid": 'wx20af16de914f1154',
                "pagepath": '/pages/index/index'
            }, {
                "name": "去帮忙",
                "type": 'miniprogram',
                "url": 'http://www.hbhzdtn.com',
                "appid": 'wx20af16de914f1154',
                "pagepath": '/pages/banzu/banzu'
            }, {
                "name": "关于我们",
                "type": 'miniprogram',
                "url": 'http://www.hbhzdtn.com',
                "appid": 'wx20af16de914f1154',
                "pagepath": '/pages/mine/about/about'
            }]
        }
        api.createMenu(menu, function (err, result) {
            if (err) {
                console.log('error', err);
            }
            onscole.log('info', 'create menu success');
        });
    }
}
module.exports = app