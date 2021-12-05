module.exports = {
	// https://cli.vuejs.org/config/#devserver-proxy
	pages: {
		// template: "public/index.html" se podrazumeva
		index: {
			entry: "src/pages/index/main.js",
			title: "Adventure Time",
		},
		RegisteredUser: {
			entry: "src/pages/registeredUser/main.js",
			title: "REGISTERED USER PAGE",
		},
	},
	devServer: {
		port: 3000,
		proxy: {
			'/api': {
				target: 'http://localhost:8080/',
				ws: true,
				changeOrigin: true
			}
		}
	}
}