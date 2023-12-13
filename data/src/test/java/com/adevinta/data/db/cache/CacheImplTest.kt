import com.adevinta.data.db.cache.CacheImpl
import com.adevinta.data.db.cache.safeRead
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CacheImplTest {

    private lateinit var cacheImpl: CacheImpl

    @Before
    fun setUp() {
        cacheImpl = CacheImpl()
    }

    @Test
    fun `save and read returns correct data`() {
        val key = "testKey"
        val data = "testData"

        cacheImpl.save(key, data)

        val result: String? = cacheImpl.read(key)
        assertEquals(data, result)
    }

    @Test
    fun `safeRead returns correct data for correct type`() {
        val key = "testKey"
        val data = "testData"

        cacheImpl.save(key, data)

        val result: String? = cacheImpl.safeRead(key)
        assertEquals(data, result)
    }

    @Test
    fun `safeRead returns null for incorrect type`() {
        val key = "testKey"
        val data = "testData"
        cacheImpl.save(key, data)

        val result: Int? = cacheImpl.safeRead(key)
        assertNull(result)
    }
}
