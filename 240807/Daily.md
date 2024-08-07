# 2024-08-07

How to set up ssh with github
``` 
Host work.github.com
  HostName ssh.github.com
  User angelswing
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/angelswing
  Port 443


Host home.github.com
  HostName ssh.github.com
  User home
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/home
  Port 443
```


