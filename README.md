# ImageLoader
An Image loader library to load images and json and save them in memory cache.
with abillity to cancl all running tasks or spcifice one also you can remove all cache.
you can aslo add a configurable cache size.

## Getting Started
Import the library as a module to your app.
To add the max size of the cache you want to use by 
```
MindLoader.init(24)
```
## Samples 
To load image into imageview you can use the extentions function for ImageView
```
ImageView.loadImage(url,placeholder)
```

To load json object with generic model use this 
```
MindLoader.getDataLoader().loadGeneric(url,object :DataStreamMapper<ByteArray,List<CutomModel>>{
                override fun mapData(input: ByteArray): List<CutomModel> {
                    return Gson().fromJson(String(input),
                        Array<CutomModel>::class.java).toList()
                }

            }) { result, exception ->
                if (result != null)
                    it.onSuccess(result)
                else if (exception != null)
                    it.onError(exception)
            }
        }
    }
    
