const db = require("./../db/mysqlHelper.js");

const app = {
  async add(args) {
    let sql = "INSERT INTO wxmp_user (openid,wx_id) value(?,?)";
    let params = [args.openid, args.wx_id];
    let result = await db.query(sql, params);
    return result;
  },

  async getWxuserByPhone(args) {
    let sql = "select * from userinfo where card_num=? and state = 1";
    let params = [args.card_num];
    let result = await db.query(sql, params);
    return result;
  },

  async getNoticePersons(args) {
    let sql = "select wxmp_user.openid from userinfo,wxmp_user where userinfo.wx_id=wxmp_user.wx_id and wxmp_user.state = 1 and userinfo.a_id=? and userinfo.state=1";
    let params = [args.a_id];
    let result = await db.query(sql, params);
    return result;
  },

  async setOrderNotice(args) {
    let sql = "update wxmp_user set state = ? where openid = ?";
    let params = [args.state, args.openid];
    let result = await db.query(sql, params);
    return result;
  },
  async getOrder(id) {
    let sql = 'select * from helplist where id = ?'
    let params = [id]
    let result = await db.query(sql, params)
    return result
  },
  async getList(args) {
    let result = await db.commonSelect(args);
    return result;
  }
};

module.exports = app;