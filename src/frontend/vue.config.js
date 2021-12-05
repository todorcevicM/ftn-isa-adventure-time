module.exports = {
	// https://cli.vuejs.org/config/#devserver-proxy
	pages: {
		// template: "public/index.html" se podrazumeva
		index: {
			entry: "src/pages/index/main.js",
			title: "Adventure Time",
		},
		administrator: {
			entry: "src/pages/administrator/main.js",
			title: "Admin Home Page",
		},
		registeredUser: {
			entry: "src/pages/registeredUser/main.js",
			title: "User Home Page",
		}
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