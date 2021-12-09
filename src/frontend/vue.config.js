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
		},
		cottageOwner: {
			entry: "src/pages/cottageOwner/main.js",
			title: "Cottage Owner Home Page",
		},
		fishingInstructor: {
			entry: "src/pages/fishingInstructor/main.js",
			title: "Fishing Instructor Home Page",
		},
		boatOwner: {
			entry: "src/pages/boatOwner/main.js",
			title: "Boat Owner Home Page",
		},
		cottage: {
			entry: "src/pages/cottage/main.js",
			title: "Cottage Home Page",
		},
		adventure: {
			entry: "src/pages/adventure/main.js",
			title: "Adventure Home Page",
		},
		boat: {
			entry: "src/pages/boat/main.js",
			title: "Boat Home Page",
		},
		registeredUserProfile: {
			entry: "src/pages/registeredUserProfile/main.js",
			title: "User Profile Page",
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