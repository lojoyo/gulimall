/**
 * 开发环境
 */
;(() => {
  window.SITE_CONFIG = {}

  // api接口请求地址 (因为是微服务，所以统一请求到API网关，而不是每个微服务)
  window.SITE_CONFIG['baseUrl'] = 'http://localhost:88/api'

  // cdn地址 = 域名 + 版本号
  window.SITE_CONFIG['domain'] = './' // 域名
  window.SITE_CONFIG['version'] = ''   // 版本号(年月日时分)
  window.SITE_CONFIG['cdnUrl'] = window.SITE_CONFIG.domain + window.SITE_CONFIG.version
})()
