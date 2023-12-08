package com.raul.axpe_prueba_tecnica

import UserData
import UserDataResponse
import com.google.gson.Gson
import com.raul.axpe_prueba_tecnica.data.RandomUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test


class RandomUserRepositoryTest : RandomUserRepository {
    override suspend fun getUsers(page: Int, results: Int): Flow<UserDataResponse> = flow {

        val data = Gson().fromJson<UserDataResponse>(
            "{\n" +
                    "  \"results\": [\n" +
                    "    {\n" +
                    "      \"gender\": \"female\",\n" +
                    "      \"name\": {\n" +
                    "        \"title\": \"Mrs\",\n" +
                    "        \"first\": \"Trupti\",\n" +
                    "        \"last\": \"Uchil\"\n" +
                    "      },\n" +
                    "      \"location\": {\n" +
                    "        \"street\": {\n" +
                    "          \"number\": 7771,\n" +
                    "          \"name\": \"Marine Drive\"\n" +
                    "        },\n" +
                    "        \"city\": \"Kamarhati\",\n" +
                    "        \"state\": \"Himachal Pradesh\",\n" +
                    "        \"country\": \"India\",\n" +
                    "        \"postcode\": 96602,\n" +
                    "        \"coordinates\": {\n" +
                    "          \"latitude\": \"4.4104\",\n" +
                    "          \"longitude\": \"100.0456\"\n" +
                    "        },\n" +
                    "        \"timezone\": {\n" +
                    "          \"offset\": \"-11:00\",\n" +
                    "          \"description\": \"Midway Island, Samoa\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"email\": \"trupti.uchil@example.com\",\n" +
                    "      \"login\": {\n" +
                    "        \"uuid\": \"ef90c876-fa94-4a34-b73c-b9ef6d2b1dd1\",\n" +
                    "        \"username\": \"silverpanda621\",\n" +
                    "        \"password\": \"cinema\",\n" +
                    "        \"salt\": \"OJrNnrDE\",\n" +
                    "        \"md5\": \"40e4c51a0a5f75b32823540beecb2dfa\",\n" +
                    "        \"sha1\": \"46091890240c2c2d52e9c4f66cac9b6594cb991f\",\n" +
                    "        \"sha256\": \"98ca4c3b03703ef12ae05319dbe123ceddee2713a155accf1d2ae11868fe7bd1\"\n" +
                    "      },\n" +
                    "      \"dob\": {\n" +
                    "        \"date\": \"1996-04-25T14:41:56.978Z\",\n" +
                    "        \"age\": 27\n" +
                    "      },\n" +
                    "      \"registered\": {\n" +
                    "        \"date\": \"2022-02-10T16:43:10.822Z\",\n" +
                    "        \"age\": 1\n" +
                    "      },\n" +
                    "      \"phone\": \"9171880169\",\n" +
                    "      \"cell\": \"9088112120\",\n" +
                    "      \"id\": {\n" +
                    "        \"name\": \"UIDAI\",\n" +
                    "        \"value\": \"481401213429\"\n" +
                    "      },\n" +
                    "      \"picture\": {\n" +
                    "        \"large\": \"https://randomuser.me/api/portraits/women/37.jpg\",\n" +
                    "        \"medium\": \"https://randomuser.me/api/portraits/med/women/37.jpg\",\n" +
                    "        \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/37.jpg\"\n" +
                    "      },\n" +
                    "      \"nat\": \"IN\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"info\": {\n" +
                    "    \"seed\": \"8d5813eb02aa437e\",\n" +
                    "    \"results\": $results,\n" +
                    "    \"page\": $page,\n" +
                    "    \"version\": \"1.4\"\n" +
                    "  }\n" +
                    "}", UserDataResponse::class.java
        )
        emit(data)

    }

}

class ViewModelTest() {
    private val repositoryTest: RandomUserRepositoryTest = RandomUserRepositoryTest()
    private var _users = MutableStateFlow<List<UserData>>(mutableListOf())


    @Test
    fun collection() = runTest {
        var userResponse: Flow<UserDataResponse>?
        launch {
            userResponse = repositoryTest.getUsers(1,1)
            val item = userResponse!!.first()
            val usersCount = item.results.count()
            assertEquals(usersCount, 1)
        }
        launch {
            var page = 1
            val results = 10
            userResponse = repositoryTest.getUsers(page,results)
            userResponse!!.flowOn(Dispatchers.Default).collect {    userResponse ->

                _users.update {
                    it + userResponse.results
                }
                val usersCount = _users.first().count()
                assertNotEquals(usersCount,2)
                assertEquals(usersCount, 1)

            }
            page++
            userResponse = repositoryTest.getUsers(page,results)
            userResponse!!.flowOn(Dispatchers.Default).collect {    userResponse ->

                _users.update {
                    it + userResponse.results
                }

                val usersCount = _users.first().count()
                assertNotEquals(usersCount,1)
                assertEquals(usersCount, 2)

            }
        }
    }
}