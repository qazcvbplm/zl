const router = require('koa-router')()
const bll = require('./../pub/bll/wxmp.js')
const bll2 = require('./../pub/bll/notice')
router.prefix('/api/wxmp')

router.get('/check/dev', async (ctx, next) => {
    let result = await bll.checkDev(ctx)
    ctx.body = result;
})
router.post('/check/dev', async (ctx, next) => {
    let result = await bll.getText(ctx)
    ctx.body = result;
})
router.get('/sendNotice', async (ctx, next) => {
    let result = await bll2.sendNotice(ctx)
    ctx.body = result;
})
router.get('/setMenu', async (ctx, next) => {
    let result = await bll2.setMeun(ctx)
    ctx.body = result;
})

module.exports = router