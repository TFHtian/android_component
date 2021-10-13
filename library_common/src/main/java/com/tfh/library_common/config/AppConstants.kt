package com.tfh.library_common.config

/**
 * 常量管理类
 */
interface AppConstants {

    object KvKey {
        const val LOGIN_ACCOUNT: String = "login_account"
        const val LOGIN_PASSWORD: String = "login_password"
    }

    object CacheKey{

    }

    /**
     * value规则： /(module后缀)/(所在类名)
     * 路由 A_ : Activity  /  F_ : Fragment
     */
    interface Router {

        object Auth {
            const val A_AUTH = "/auth/AuthCheckActivity"
        }

        object Main {
            const val A_MAIN = "/main/MainActivity"
        }

        object Home {
            const val F_HOME = "/home/HomeFragment"
        }

        object Square {
            const val F_SQUARE = "/square/SquareFragment"
        }

        object Project {
            const val F_PROJECT = "/project/ProjectFragment"
        }

        object Mine {
            const val F_MINE = "/mine/MineFragment"
        }

    }

    object BundleKey {

    }

    object Constants {

    }

}